package me.brandonray.doordashlite.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import me.brandonray.doordashlite.R;
import me.brandonray.doordashlite.models.Restaurant;

public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ImageView restaurantImage;
    private final TextView restaurantName;
    private final TextView restaurantDescription;
    private final TextView restaurantStatus;

    private Restaurant restaurant;
    private Context context;

    public RestaurantViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;

        restaurantImage = itemView.findViewById(R.id.restaurantImage);
        restaurantName = itemView.findViewById(R.id.restaurantName);
        restaurantDescription = itemView.findViewById(R.id.restaurantDescription);
        restaurantStatus = itemView.findViewById(R.id.restaurantStatus);

        itemView.setOnClickListener(this);
    }

    public void bindRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;

        Picasso.get().load(restaurant.getCover_img_url()).into(restaurantImage);
        this.restaurantName.setText(restaurant.getName());
        this.restaurantDescription.setText(restaurant.getDescription());
        this.restaurantStatus.setText(restaurant.getStatus());
    }

    @Override
    public void onClick(View view) {
        if (this.restaurant != null) {
            Toast.makeText(this.context, "Clicked on " + this.restaurant.getName(), Toast.LENGTH_LONG ).show();
        }

    }
}
