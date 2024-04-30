package model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingPOJOLombok {
    private String name;
    private int category_id;
    private int price;
    private String release_date;
    private List <Integer> image_ids;
    private boolean status;

}
