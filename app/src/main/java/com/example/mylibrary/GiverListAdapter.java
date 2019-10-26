package com.example.mylibrary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
public class GiverListAdapter extends RecyclerView.Adapter<GiverListAdapter.GiverViewHolder> {
    private List<Business> mGivers;
    private Context mContext;

    public GiverListAdapter(Context context, List<Business> givers) {
        mContext = context;
        mGivers = givers;
    }
    @Override
    public GiverListAdapter.GiverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.givers_list, parent, false);
        GiverViewHolder viewHolder = new GiverViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GiverListAdapter.GiverViewHolder holder, int position) {
        holder.bindRestaurant(mGivers.get(position));
    }

    @Override
    public int getItemCount() {
        return mGivers.size();
    }
    public class GiverViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.restaurantImageView)
        ImageView mRestaurantImageView;
        @BindView(R.id.giverNameTextView)
        TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;


        private Context mContext;

        public GiverViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindRestaurant(Business center) {
            Picasso.get().load(center.getImageUrl()).into(mRestaurantImageView);
            mNameTextView.setText(center.getName());
            mCategoryTextView.setText(center.getCategories().get(0).getTitle());
            mRatingTextView.setText("Rating: " + center.getRating() + "/5");
        }
        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, GiversDetailsActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("restaurants", Parcels.wrap(mGivers));
            mContext.startActivity(intent);
        }

    }

}
