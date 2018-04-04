package veltektrio.wishlist_project;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddWishActivity extends AppCompatActivity {

    private String name;
    private String size;
    private String link;
    private double price;
    private String color;
    private String shop;
    private String note;

    @BindView(R.id.addWishButton2)
    public Button addWishButton;

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
        setContentView(R.layout.activity_add_wish);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.addWishButton2)
    public void addWishClick() {
        System.out.println("TRYK");
        //tester om editText er tomme
        if (!TextUtils.isEmpty(et_name.getText()))
            name = et_name.getText().toString();
        if (!TextUtils.isEmpty(et_size.getText()))
            size = et_size.getText().toString();
        if (!TextUtils.isEmpty(et_link.getText()))
            link = et_link.getText().toString();
        if (!TextUtils.isEmpty(et_price.getText()))
            price = Double.parseDouble(et_price.getText().toString());
        if (!TextUtils.isEmpty(et_color.getText()))
            color = et_color.getText().toString();
        if (!TextUtils.isEmpty(et_shop.getText()))
            shop = et_shop.getText().toString();
        if (!TextUtils.isEmpty(et_note.getText()))
            note = et_note.getText().toString();
        //Opretter wish med alle de felter der er

        Wish newWish = new Wish(name, size, link, price, color, shop, note);

        MyWishListActivity.myWishlist.add_wish(newWish);

        finish();
    }
}
