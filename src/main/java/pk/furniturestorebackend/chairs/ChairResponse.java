package pk.furniturestorebackend.chairs;

import lombok.Getter;
import lombok.Setter;
import pk.furniturestorebackend.database.furniture.chairs.Chair;

import java.util.List;

@Getter
@Setter
public class ChairResponse {
    private int totalNumberOfPages;
    private List<Chair> chairsFromSelectedPage;

    public ChairResponse() {
    }

    public ChairResponse(int totalNumberOfPages, List<Chair> chairsFromSelectedPage) {
        this.totalNumberOfPages = totalNumberOfPages;
        this.chairsFromSelectedPage = chairsFromSelectedPage;
    }
}
