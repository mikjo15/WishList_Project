package veltektrio.wishlist_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemListFragment extends Fragment {

    @BindView(R.id.itemlist_recycler_view) // Her bindes recyclerviewet der viser ønskerne
    public RecyclerView recyclerView;

    // Layoutmanager instantieres
    private RecyclerView.LayoutManager layoutManager;

    // Datareference bruges til at koble til db. Det er egentlig bare et link til Firebase på nettet
    private DatabaseReference mDatabase;

    // String der bruges til at identificere hvilken activity vi kommer fra
    String activity_from_intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Vi henter stringen fra en intent
        activity_from_intent = getActivity().getIntent().getStringExtra("activity").trim();

        // Da vi går ind i fragmentet inden vi har sat et argument, bliver vi nødt til at have en dødemandsknap,
        // så der vil blive sat en database op uanset hvad
        String uid;
        uid = getActivity().getIntent().getStringExtra("refToUserID");
        mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("wishes");

        // Vi sørger for at data synkroniseres, så det kan tilgås i offline mode
        mDatabase.keepSynced(true);

        // Layoutmanageren sættes
        layoutManager = new LinearLayoutManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Vi inflater layoutet til dette fragment
        View v = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this,v);

        recyclerView.setLayoutManager(layoutManager);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Vi laver en adapter til firebasen
        final FirebaseRecyclerAdapter<Wish, WishViewHolder> firebaseRecyclerAdapter;
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Wish, WishViewHolder>
                (Wish.class, R.layout.item_list_recycleview, WishViewHolder.class, mDatabase) {

            // Der sørges for at der kun bliver vist den data vi har på wishet
            @Override
            protected void populateViewHolder(WishViewHolder holder, Wish currentwish, final int position) {
                if(currentwish.getName() != null)
                    holder.input_name.setText(currentwish.getName());
                else {
                    holder.input_name.setVisibility(View.GONE);
                    holder.text_name.setVisibility(View.GONE);
                }

                if(currentwish.getItemSize() != null)
                    holder.input_itemSize.setText(currentwish.getItemSize());
                else {
                    holder.input_itemSize.setVisibility(View.GONE);
                    holder.text_itemSize.setVisibility(View.GONE);
                }

                if(currentwish.getUrl() != null)
                    holder.input_url.setText(currentwish.getUrl());
                else {
                    holder.input_url.setVisibility(View.GONE);
                    holder.text_url.setVisibility(View.GONE);
                }

                if(currentwish.getPrice() != null)
                    holder.input_price.setText(currentwish.getPrice());
                else {
                    holder.input_price.setVisibility(View.GONE);
                    holder.text_price.setVisibility(View.GONE);
                }

                if(currentwish.getNote() != null)
                    holder.input_note.setText(currentwish.getNote());
                else {
                    holder.input_note.setVisibility(View.GONE);
                    holder.text_note.setVisibility(View.GONE);
                }

                if(currentwish.getColor() != null)
                    holder.input_color.setText(currentwish.getColor());
                else {
                    holder.input_color.setVisibility(View.GONE);
                    holder.text_color.setVisibility(View.GONE);
                }

                if(currentwish.getShop() != null)
                    holder.input_shop.setText(currentwish.getShop());
                else {
                    holder.input_shop.setVisibility(View.GONE);
                    holder.text_shop.setVisibility(View.GONE);
                }

                if(activity_from_intent.equals("friends")) {
                    holder.editButton.setVisibility(View.GONE);
                    holder.deleteButton.setVisibility(View.GONE);
                }

                // Her sættes en onClickListener til deleteknappen
                // Listeneren laves her inde, da vi kan bruge position til at tilgå elemtet i databasen
                holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("clicked", "Deleted");
                        getRef(position).removeValue();
                    }
                });

                holder.editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("clicked", "Edited wish");
                        String refToWish = getRef(position).toString();
                        Intent intent = new Intent(getContext(), AddWishActivity.class);
                        intent.putExtra("refToWish", refToWish);
                        startActivity(intent);
                    }
                });
            }

        };
        recyclerView.setAdapter(firebaseRecyclerAdapter); // Adapteren sættes
    }

    // Viewholderen bruges til at instantiere og binde viewsne
    public static class WishViewHolder extends RecyclerView.ViewHolder {

        public TextView input_name;
        public TextView input_itemSize;
        public TextView input_url;
        public TextView input_price;
        public TextView input_note;
        public TextView input_color;
        public TextView input_shop;

        public TextView text_name;
        public TextView text_itemSize;
        public TextView text_url;
        public TextView text_price;
        public TextView text_note;
        public TextView text_color;
        public TextView text_shop;

        public ImageButton deleteButton;
        public ImageButton editButton;

        public WishViewHolder(View v) {
            super(v);
            input_name = v.findViewById(R.id.InputText_name);
            input_itemSize = v.findViewById(R.id.InputText_size);
            input_url = v.findViewById(R.id.InputText_link);
            input_price = v.findViewById(R.id.InputText_price);
            input_note = v.findViewById(R.id.InputText_note);
            input_color = v.findViewById(R.id.InputText_color);
            input_shop = v.findViewById(R.id.InputText_shop);

            text_name = v.findViewById(R.id.textView_name);
            text_itemSize = v.findViewById(R.id.textView_size);
            text_url = v.findViewById(R.id.textView_link);
            text_price = v.findViewById(R.id.textView_price);
            text_note = v.findViewById(R.id.textView_note);
            text_color = v.findViewById(R.id.textView_color);
            text_shop = v.findViewById(R.id.textView_shop);

            deleteButton = v.findViewById(R.id.deleteWishButton);
            editButton = v.findViewById(R.id.editWishButton);
        }
    }
}
