package fr.epita.kesKonAVu.exposition.common;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Abstract generic implementation of a bean mapper.
 *
 * @param <T> The source class
 * @param <S> The target class
 */
public abstract class AbstractMapper<T, S> {

	/**
	 * Map an entity to a Dto
	 *
	 * @param entity entity
	 * @return the mapped dto
	 */
	public abstract S mapToDto(T entity);

	/**
	 * Map a Dto to an entity
	 *
	 * @param dto dto
	 * @return the mapped entity
	 */
	public abstract T mapToEntity(S dto);

	/**
	 * Map an entity list to a Dto list
	 *
	 * @param entityList entityList
	 * @return a List of the mapped entity
	 */
	public List<S> mapToDtoList(final List<T> entityList) {
		return entityList.stream().filter(Objects::nonNull).map(this::mapToDto).collect(Collectors.toList());
	}

	/**
	 * Map a Dto list to an entity list
	 *
	 * @param dtoList dtoList
	 * @return a List of the mapped entity
	 */
	public List<T> mapToEntityList(final List<S> dtoList) {
		return dtoList.stream().filter(Objects::nonNull).map(this::mapToEntity).collect(Collectors.toList());
	}

}
