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

    // Vi får fat i den bruger der er logget ind
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    // TODO: Dette skal skiftes ud med en intent, så vi ud fra denne intent kan bestemme om det er mine eller friends der skal vises i fragment
    // Når jeg forsøger at sende et argument med, sættes det ikke før fragmentet er infalted, det har derfor ikke kunne bruges
    // til at bestemme om det ene eller det andet skal stå

    String activity_from_intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity_from_intent = getActivity().getIntent().getStringExtra("activity").trim();

        // Da vi går ind i fragmentet inden vi har sat et argument, bliver vi nødt til at have en dødemandsknap,
        // så der vil blive sat en database op uanset hvad
        String uid;
        //uid = firebaseUser.getUid();
        uid = getActivity().getIntent().getStringExtra("refToUserID");
        mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("wishes");

        /*
        // Skal fragmentet åbne mine-listen
        if(getArguments() != null) {
            Bundle bundle = getArguments();
            Boolean myBool = bundle.getBoolean("mine"); // når vi kommer fra friends skal argumentet sættes til false
            if(myBool) {
                uid = firebaseUser.getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("wishes");
            } else {
                uid = bundle.getString("friendid"); // Når man kommer fra friends skal der sendes et argument med key friendid
                mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("wishes"); //
            }
        } */

        mDatabase.keepSynced(true);

        // Denne liste skal eksistere, jeg er ikke sikker på hvorfor. Binder muligvis fragment og activity sammen
        MyWishListActivity.myWishlist = new Wishlist("Test wishlist");

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
        final FirebaseRecyclerAdapter<Wish, WishViewHolder> firebaseRecyclerAdapter;
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Wish, WishViewHolder>
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
