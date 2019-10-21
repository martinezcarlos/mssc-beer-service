package mart.karle.msscbeerservice.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerPagedList extends PageImpl<BeerDto> {
  private static final long serialVersionUID = -2718399308467384233L;

  public BeerPagedList(final List<BeerDto> content, final Pageable pageable, final long total) {
    super(content, pageable, total);
  }

  public BeerPagedList(final List<BeerDto> content) {
    super(content);
  }
}
