package veltektrio.wishlist_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ItemListFragment extends Fragment {
    private CardView MyCardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //MyCardView = container.findViewById(R.id.card_view);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }
}
