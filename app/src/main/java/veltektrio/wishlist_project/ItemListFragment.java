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

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemListFragment extends Fragment {
    private CardView MyCardView;

    @BindView(R.id.itemlist_recycler_view)
    public RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Dummy data
        MyWishListActivity.myWishlist = new Wishlist("Test wishlist");
        Wish firstWish = new Wish("name", "size", "url", 66, "note", "color", "shop");
        Wish firstWish2 = new Wish("name2", "size2", "url2", 67, "note2", "color2", "shop2");
        Wish firstWish3 = new Wish("name3", "size3", "url3", 68, "note3", "color3", "shop3");
        MyWishListActivity.myWishlist.add_wish(firstWish);
        MyWishListActivity.myWishlist.add_wish(firstWish2);
        MyWishListActivity.myWishlist.add_wish(firstWish3);

        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new WishListAdapter(MyWishListActivity.myWishlist);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //MyCardView = container.findViewById(R.id.card_view);
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this,v);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return v;
    }
}
