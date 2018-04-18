package veltektrio.wishlist_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemListFragment extends Fragment {
    private CardView MyCardView;

    @BindView(R.id.itemlist_recycler_view) // Her bindes recyclerviewet der viser ønskerne
    public RecyclerView recyclerView;

    // private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // Datareference bruges til at koble til db. Det er egentlig bare et link til Firebase på nettet
    private DatabaseReference mDatabase;
    private String child;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // En streng hentes fra activity der starter fragmentet
        // Key er database og value er enten Mine eller Friends
        // Det er meningen at dette skal afgøre om fragmentet skal tilgå databasen med egne elller venners ønsker
        Bundle bundle = getArguments();

        if(bundle != null) {
            child = bundle.getString("database");
            if (child == "Mine") {
                mDatabase = FirebaseDatabase.getInstance().getReference().child(child);
            } else {
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Friends").child(child);
            }
        } else { // For at undgå crash
            mDatabase = FirebaseDatabase.getInstance().getReference().child("mikjo15");
        }
        mDatabase.keepSynced(true);

        // Denne liste skal eksistere, jeg er ikke sikker på hvorfor. Binder muligvis fragment og activity sammen
        MyWishListActivity.myWishlist = new Wishlist("Test wishlist");

        layoutManager = new LinearLayoutManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //MyCardView = container.findViewById(R.id.card_view);
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this,v);

        recyclerView.setLayoutManager(layoutManager);
        // recyclerView.setAdapter(adapter);
        return v;
    }


    // Alt det der står i onStart skal muligvis rykkes, så det ikke gælder for alle steder hvor
    // dette fragment bruges.
    // FirebaseRecyclerAdapter er fundet i en tutorial. Bruges til at sætte views til diverse værdier
    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Wish, WishViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Wish, WishViewHolder>
                (Wish.class, R.layout.item_list_recycleview, WishViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(WishViewHolder holder, Wish currentwish, int position) {
                if(currentwish.getName() != "") // Alle if'sne kan sættes i en funktion
                    holder.input_name.setText(currentwish.getName());
                else {
                    holder.input_name.setVisibility(View.GONE);
                    holder.text_name.setVisibility(View.GONE);
                }

                if(currentwish.getItemSize() != "")
                    holder.input_itemSize.setText(currentwish.getItemSize());
                else {
                    holder.input_itemSize.setVisibility(View.GONE);
                    holder.text_itemSize.setVisibility(View.GONE);
                }

                if(currentwish.getUrl() != "")
                    holder.input_url.setText(currentwish.getUrl());
                else {
                    holder.input_url.setVisibility(View.GONE);
                    holder.text_url.setVisibility(View.GONE);
                }

                if(currentwish.getPrice() != 0)
                    holder.input_price.setText(Double.toString(currentwish.getPrice()));
                else {
                    holder.input_price.setVisibility(View.GONE);
                    holder.text_price.setVisibility(View.GONE);
                }

                if(currentwish.getNote() != "")
                    holder.input_note.setText(currentwish.getNote());
                else {
                    holder.input_note.setVisibility(View.GONE);
                    holder.text_note.setVisibility(View.GONE);
                }

                if(currentwish.getColor() != "")
                    holder.input_color.setText(currentwish.getColor());
                else {
                    holder.input_color.setVisibility(View.GONE);
                    holder.text_color.setVisibility(View.GONE);
                }

                if(currentwish.getShop() != "")
                    holder.input_shop.setText(currentwish.getShop());
                else {
                    holder.input_shop.setVisibility(View.GONE);
                    holder.text_shop.setVisibility(View.GONE);
                }
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
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
        }
    }
}
