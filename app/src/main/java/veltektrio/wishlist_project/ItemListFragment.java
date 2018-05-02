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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemListFragment extends Fragment {

    @BindView(R.id.itemlist_recycler_view) // Her bindes recyclerviewet der viser ønskerne
    public RecyclerView recyclerView;

    // private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // Datareference bruges til at koble til db. Det er egentlig bare et link til Firebase på nettet
    private DatabaseReference mDatabase;

    private String uid;

    String activity_from_intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activity_from_intent = getActivity().getIntent().getStringExtra("activity").trim();

        if(activity_from_intent.equals("friend"))
            uid = getActivity().getIntent().getStringExtra("refToUserID");
        else
            uid = getActivity().getIntent().getStringExtra("refToUserID");


        mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("wishes");
        mDatabase.keepSynced(true);

        // Denne liste skal eksistere, jeg er ikke sikker på hvorfor. Binder muligvis fragment og activity sammen
        MyWishListActivity.myWishlist = new Wishlist("user");

        layoutManager = new LinearLayoutManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this,v);

        recyclerView.setLayoutManager(layoutManager);
        return v;
    }


    // Alt det der står i onStart skal muligvis rykkes, så det ikke gælder for alle steder hvor
    // dette fragment bruges.
    // FirebaseRecyclerAdapter er fundet i en tutorial. Bruges til at sætte views til diverse værdier
    @Override
    public void onStart() {
        super.onStart();
        final FirebaseRecyclerAdapter<Wish, WishViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Wish, WishViewHolder>
                (Wish.class, R.layout.item_list_recycleview, WishViewHolder.class, mDatabase) {

            @Override
            protected void populateViewHolder(WishViewHolder holder, Wish currentwish, final int position) {
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

                if(currentwish.getPrice() != "")
                    holder.input_price.setText(currentwish.getPrice());
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

                if(activity_from_intent.equals("friends"))
                {
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
                    };
                });

                holder.editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("clicked", "Edited wish");
                        String refToWish = getRef(position).toString();
                        Intent intent = new Intent(getContext(), AddWishActivity.class);
                        intent.putExtra("refToWish", refToWish);
                        intent.putExtra("activity", "edit_wish");
                        startActivity(intent);
                    }
                });
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
