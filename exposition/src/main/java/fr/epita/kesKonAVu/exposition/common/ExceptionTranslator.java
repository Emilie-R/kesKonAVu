package fr.epita.kesKonAVu.exposition.common;

import fr.epita.kesKonAVu.domain.common.AlreadyExistingException;
import fr.epita.kesKonAVu.domain.common.DataFormatException;
import fr.epita.kesKonAVu.domain.common.InvalidCredentialsException;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionTranslator.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        final String errors = ex.getBindingResult() //
                .getFieldErrors().stream() //
                .map(x -> x.getDefaultMessage()) //
                .collect(Collectors.joining(","));

        final ErrorModel apiError = ErrorModel.builder() //
                .code("") //
                .message(errors) //
                .description("Please check your parameters")//
                .build();

        LOG.info(ex.getMessage());
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseBody
    public ResponseEntity<Object> handleIllegalArgumentException(final IllegalArgumentException ex) {

        final ErrorModel apiError = ErrorModel.builder() //
                .message(ex.getMessage()) //
                .description("Please check your parameters")//
                .build();
        LOG.warn(ex.getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { InvalidCredentialsException.class })
    @ResponseBody
    public ResponseEntity<Object> handleInvalidCredentialsException(final InvalidCredentialsException ex) {

        final ErrorModel apiError = ErrorModel.builder() //
                .message("Invalid pseudo or password") //
                .description("Invalid credentials : please check your pseudo and password")//
                .build();
        LOG.error(ex.getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = { NotFoundException.class })
    @ResponseBody
    public ResponseEntity<Object> handleNotFoundException(final NotFoundException ex) {

        final ErrorModel apiError = ErrorModel.builder() //
                .message(ex.getLocalizedMessage()) //
                .description("")//
                .build();
        LOG.warn(ex.getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { AlreadyExistingException.class })
    @ResponseBody
    public ResponseEntity<Object> handleAlreadyExistingException(final AlreadyExistingException ex) {

        final ErrorModel apiError = ErrorModel.builder() //
                .message(ex.getLocalizedMessage()) //
                .build();
        LOG.warn(ex.getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { DataFormatException.class })
    @ResponseBody
    public ResponseEntity<Object> handleDataFormatException(final DataFormatException ex) {

        final ErrorModel apiError = ErrorModel.builder() //
                .message(ex.getLocalizedMessage()) //
                .description("")//
                .build();
        LOG.warn(ex.getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ResponseEntity<Object> handleOthers(final Exception ex) {

        final ErrorModel apiError = ErrorModel.builder() //
                .code("err.internal") //
                .message(ex.getLocalizedMessage()) //
                .description("internal error occurred, please contact your administrator")//
                .build();
        LOG.error(ex.getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<Object> accessDeniedExceptionHandler(final AccessDeniedException ex) {

        final ErrorModel apiError = ErrorModel.builder() //
                .message(ex.getLocalizedMessage()) //
                .description("You have'nt access privilege to this operation")//
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

}
