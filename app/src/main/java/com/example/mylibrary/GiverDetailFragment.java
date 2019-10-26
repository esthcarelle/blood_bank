package com.example.mylibrary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiverDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.giverImageView)
    ImageView mImageLabel;
    @BindView(R.id.giverNameTextView)
    TextView mNameLabel;
    @BindView(R.id.cuisineTextView)
    TextView mCategoriesLabel;
    @BindView(R.id.ratingTextView)
    TextView mRatingLabel;
    @BindView(R.id.websiteTextView)
    TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView)
    TextView mPhoneLabel;
    @BindView(R.id.addressTextView)
    TextView mAddressLabel;
    @BindView(R.id.saveRestaurantButton)
    TextView mSaveRestaurantButton;

    private Business mCenter;

    public GiverDetailFragment() {
        // Required empty public constructor
    }

    public static GiverDetailFragment newInstance(Business center) {
        GiverDetailFragment restaurantDetailFragment = new GiverDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("restaurant", Parcels.wrap(center));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCenter = Parcels.unwrap(getArguments().getParcelable("restaurant"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.giver_fragment, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mCenter.getImageUrl()).into(mImageLabel);
        List<String> categories = new ArrayList<>();

        for (Category category: mCenter.getCategories()) {
            categories.add(category.getTitle());
        }

        mNameLabel.setText(mCenter.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", categories));
        mRatingLabel.setText(Double.toString(mCenter.getRating()) + "/5");
        mPhoneLabel.setText(mCenter.getPhone());
        mAddressLabel.setText(mCenter.getLocation().toString());
        mSaveRestaurantButton.setOnClickListener(this);


        return view;
    }
    @Override
    public void onClick(View v) {
        if (v == mSaveRestaurantButton) {
            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_CENTERS);
            restaurantRef.push().setValue(mCenter);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

}
