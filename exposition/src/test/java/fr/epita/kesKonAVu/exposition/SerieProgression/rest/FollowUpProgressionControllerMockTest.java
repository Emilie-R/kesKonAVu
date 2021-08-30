package fr.epita.kesKonAVu.exposition.SerieProgression.rest;

//
//
//@WebMvcTest(FollowUpProgressionController.class)
//public class FollowUpProgressionControllerMockTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @LocalServerPort
//    private int port;
//    private String baseURL;
//
//    @Autowired
//    FollowUpRepository followUpRepository;
//
//    @BeforeEach
//    public void setUp() {
//        baseURL = "http://localhost:" + this.port + "/v1/progression/";
//    }
//
//    @Test
//    public void URLSaveSerieProgression() throws Exception
//    {
//        FollowUp followUp = followUpRepository.findByIdWithAllEpisodeFollowUps(3L);
//        FollowUpProgressionDTOMapper mapper = new FollowUpProgressionDTOMapper();
//        FollowUpProgressionDTO followUpProgession = mapper.mapToDto(followUp);
//        followUpProgession.getEpisodeFollowUpDTOList()
//                .stream()
//                .forEach(e -> e.setEpisodeStatusEnum(EpisodeStatusEnum.AVOIR)); // tous les épisodes "A VOIR"
//        mvc.perform( MockMvcRequestBuilders
//                        .post(baseURL+"save")
//                        .content(asJsonString(followUpProgession))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
////                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//}
