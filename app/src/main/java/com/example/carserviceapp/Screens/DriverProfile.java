package com.example.carserviceapp.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carserviceapp.R;
import com.example.carserviceapp.data.CarModel;
import com.example.carserviceapp.data.CarInfoViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DriverProfile extends AppCompatActivity {

    private RecyclerView requestsRecyclerView;
    private ArrayList<CarModel> carsArrayList;
    private FirebaseRecyclerOptions<CarModel> options;
    private FirebaseRecyclerAdapter<CarModel, CarInfoViewHolder> adapter;
    private FirebaseDatabase database;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);

        requestsRecyclerView = findViewById(R.id.requests);
        requestsRecyclerView.setHasFixedSize(true);
        carsArrayList = new ArrayList<CarModel>();
        requestsRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        database = FirebaseDatabase.getInstance();


        getRequests();

        adapter.startListening();

        requestsRecyclerView.setAdapter(adapter);



    }


    void getRequests(){
        mDatabaseReference = database.getReference().child("Requests");
        mDatabaseReference.keepSynced(true);

        options = new FirebaseRecyclerOptions.Builder<CarModel>().
                setQuery(mDatabaseReference, CarModel.class)
                .build();


        adapter =new FirebaseRecyclerAdapter<CarModel, CarInfoViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CarInfoViewHolder holder, int position,
                                            @NonNull CarModel model) {
                System.out.println("baby 2");

                if (model.getModel() != null) {
                    carsArrayList.add(model);

                    holder.details.setText(model.getModel());

                    holder.acceptB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(v.getContext(),MyMapScreen.class);

                            intent.putExtra("MyClass", model);

                            startActivity(intent);
                        }
                    });

                    System.out.println("baby = "+ model.getModel());
//                    final DataSnapshot snapshot = options.getSnapshots().getSnapshot(position);
//                    final String followUserId = snapshot.getKey();
//
//                    String ss = (String) snapshot.child("profileInfo").child("username").getValue();
//
//
//                    holder.personName.setText("Modajs");
//                    holder.followersNumber.setText(55 + "");
//                    holder.personName.setText(model.getProfileInfo().getUsername());
//                    if (model.getProfileImage() != null) {
//                        Glide.with(getActivity().getApplicationContext()).load(model.getProfileImage().getUrl()
//                        ).into(holder.profileImage);
//                    }
//
//                    holder.followButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//                            // if i follow one put its id in my network
//                            String personToFollow = model.getProfileInfo().getPersonId();
//
//                        }
//                    });

                }
            }

            @NonNull
            @Override
            public CarInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                System.out.println("baby 3");

                CarInfoViewHolder carViewHolder = new CarInfoViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.request_item, parent, false));

                return carViewHolder;
            }
        };

    }



//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }

}