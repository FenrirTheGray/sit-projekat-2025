package sitprojekat.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;

import jakarta.servlet.http.HttpServletResponse;

@Tag("div")
public class RouteNotFoundHandler extends Component implements HasErrorParameter<NotFoundException> {

    private static final long serialVersionUID = 1L;

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
        event.forwardTo(ProductsView.class);
        return HttpServletResponse.SC_FOUND;
    }
}
