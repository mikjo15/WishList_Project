package veltektrio.wishlist_project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddWishFragment extends Fragment {

    private String name;
    private String size;
    private String link;
    private double price;
    private String color;
    private String shop;
    private String note;
    private Button addWishButton;

    @BindView(R.id.editText_name)
    public EditText et_name;

    @BindView(R.id.editText_size)
    public EditText et_size;

    @BindView(R.id.editText_link)
    public EditText et_link;

    @BindView(R.id.editText_price)
    public EditText et_price;

    @BindView(R.id.editText_color)
    public EditText et_color;

    @BindView(R.id.editText_shop)
    public EditText et_shop;

    @BindView(R.id.editText_note)
    public EditText et_note;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
        addWishButton = getActivity().findViewById(R.id.button);

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_wish, container, false);

        addWishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tester om editText er tomme
                if (!TextUtils.isEmpty(et_name.getText()))
                    name = et_name.getText().toString();
                else
                    name = "";
                if (!TextUtils.isEmpty(et_size.getText()))
                    size = et_size.getText().toString();
                else
                    size = "";
                if (!TextUtils.isEmpty(et_link.getText()))
                    link = et_link.getText().toString();
                else
                    link = "";
                if (!TextUtils.isEmpty(et_price.getText()))
                    price = Double.parseDouble(et_price.getText().toString());
                else
                    price = 0.00;
                if (!TextUtils.isEmpty(et_color.getText()))
                    color = et_color.getText().toString();
                else
                    color = "";
                if (!TextUtils.isEmpty(et_shop.getText()))
                    shop = et_shop.getText().toString();
                else
                    shop = "";
                if (!TextUtils.isEmpty(et_note.getText()))
                    note = et_note.getText().toString();
                else
                    note = "";
                //Opretter wish med alle de felter der er

                Wish newWish = new Wish(name, size, link, price, color, shop, note);

                MyWishListActivity.myWishlist.add_wish(newWish);

                getActivity().getSupportFragmentManager().beginTransaction().remove().commit();
            }
        });
    }
}
