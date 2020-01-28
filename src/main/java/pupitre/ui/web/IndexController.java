package pupitre.ui.web;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.ModelAndView;
import lombok.extern.slf4j.Slf4j;
import pupitre.ui.service.CourseService;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller("/")
public class IndexController {

  private final CourseService courseService;

  public IndexController(CourseService courseService) {
    this.courseService = courseService;
  }

  @Get
  public ModelAndView index(HttpRequest<?> request) {
    var dataToRender = Map.of(
      "sliderItems", courseService.awesome(),
      "courses", courseService.popular(),
      "featuredCourse", courseService.featuredCourse(),
      "events", courseService.events(),
      "instructors", instructors(),
      "tour", tour(),
      "testimonials", testimonials()
    );

    return new ModelAndView("index", dataToRender);
  }

  private List testimonials() {
    return List.of(
      testimonial(
        "/images/team/team-01.jpg",
        "Devid Martin",
        "Guardian",
        "I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was " +
          "born and I will give you a complete account of the system, and expound the actual teachings of the " +
          "great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, " +
          "or avoids pleasure itself"
      )
    );
  }

  private Map testimonial(String image, String name, String company, String content) {
    return Map.of(
      "image", image,
      "name", name,
      "company", company,
      "content", content
    );
  }

  private Map tour() {
    return tour("/images/video.png",
      "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis corrupti quos dolores et " +
        "quas molestias excepturi sint occaecati cupiditate",
      "non provident, similique sunt in culpa qui officia deserunt mollitia animi fuga.",
      "https://www.youtube.com/watch?v=VaZ_B4HeewU");
  }

  private Map tour(String image, String desc1, String desc2, String videoUrl) {
    return Map.of(
      "image", image,
      "desc1", desc1,
      "desc2", desc2,
      "videoUrl", videoUrl

    );
  }

  private List instructors() {
    return List.of(
      instructor(
        "/images/team/team-01.jpg",
        "#",
        "Domingo Suárez",
        "Instructor"
      )
    );
  }

  private Map instructor(String logo, String profileUrl, String name, String title) {
    return Map.of(
      "logo", logo,
      "profileUrl", profileUrl,
      "name", name,
      "title", title
    );
  }
}
