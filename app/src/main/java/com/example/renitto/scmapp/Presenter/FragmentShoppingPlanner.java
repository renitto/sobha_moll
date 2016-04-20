package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renitto.scmapp.DAL.DbManager;
import com.example.renitto.scmapp.Model.ModelShoppingPlanner;
import com.example.renitto.scmapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Renitto on 2/29/2016.
 */
public class FragmentShoppingPlanner extends Fragment {

    RecyclerView RV_Shopping_Planner;
    RecyclerView.LayoutManager mLayoutManager;
   ArrayList<ModelShoppingPlanner> mItems = new ArrayList<ModelShoppingPlanner>();
    Button BT_shopping_planner_add,BT_clear_all_shopp_planner_item;
    EditText ET_shopping_planner_item_name;
    ModelShoppingPlanner shop_item;
    DbManager dbManager ;
    ShoppingPlannerItemAdapter shoppingPlannerItemAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.shopping_planner_fragment,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dbManager = new DbManager();


        RV_Shopping_Planner = (RecyclerView)view.findViewById(R.id.recycler_shopping_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        RV_Shopping_Planner.setLayoutManager(mLayoutManager);

        shoppingPlannerItemAdapter = new ShoppingPlannerItemAdapter(dbManager.getMyshoppingPlanner(getActivity()));

        RV_Shopping_Planner.setAdapter(shoppingPlannerItemAdapter);

        BT_shopping_planner_add = (Button)view.findViewById(R.id.bt_shopping_planner_add);
        BT_clear_all_shopp_planner_item = (Button)view.findViewById(R.id.bt_shopping_clear_all);

        ET_shopping_planner_item_name =(EditText)view.findViewById(R.id.et_shopping_planner_item_name);



        BT_shopping_planner_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              addToPlanner();

            }
        });

        BT_clear_all_shopp_planner_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shoppingPlannerItemAdapter.ClearAllData();
                dbManager.removeAllShoppingPlanner(getActivity());
            }
        });


        ET_shopping_planner_item_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addToPlanner();
                    return true;
                }
                return false;
            }
        });


 // swipe to remove

        ItemTouchHelper.Callback callback = new MovieTouchHelper(shoppingPlannerItemAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(RV_Shopping_Planner);







        return view;
    }


    public void  addToPlanner()
    {
        if (!ET_shopping_planner_item_name.getText().toString().equals("") && ET_shopping_planner_item_name.getText().toString().length() != 0)
        {
            shop_item = new ModelShoppingPlanner(ET_shopping_planner_item_name.getText().toString(),false);
            shoppingPlannerItemAdapter.addItem(shop_item);
            ET_shopping_planner_item_name.setText("");
            dbManager.insertToShoppingPlanner(getActivity(),shop_item);

            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            //to hide it, call the method again
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        }
    }


    public class ShoppingPlannerItemAdapter
            extends RecyclerView.Adapter<ShoppingPlannerItemAdapter.ViewHolder> {

        ArrayList<ModelShoppingPlanner> mItems = new ArrayList<ModelShoppingPlanner>();



        //type 1 viewholder
        public class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;


            public final CheckBox CB_shopping_planner_item;




            public ViewHolder(View view) {
                super(view);
                mView = view;


                CB_shopping_planner_item = (CheckBox) view.findViewById(R.id.cb_shopping_planner_item);


            }


        }


        public ShoppingPlannerItemAdapter(ArrayList mItems) {
            this.mItems = mItems;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.shopping_planner_item, parent, false);




            return new ViewHolder(view);


        }



        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {





            holder.CB_shopping_planner_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    dbManager.updateShoppingPlanner(getActivity(),new ModelShoppingPlanner(mItems.get(position).getShop_item_name(),isChecked));
                }
            });



        holder.CB_shopping_planner_item.setText(mItems.get(position).getShop_item_name());

            if (mItems.get(position).isShop_status())
            {
                holder.CB_shopping_planner_item.setTextColor(getResources().getColor(R.color.colorPrimary));
                holder.CB_shopping_planner_item.setChecked(true);
            }









            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });







        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }


        private void addItem(ModelShoppingPlanner item) {
            mItems.add(item);
            ShoppingPlannerItemAdapter.this.notifyItemInserted(mItems.size()-1);
        }


        private void ClearAllData() {
            int size = this.mItems.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    this.mItems.remove(0);
                }

                this.notifyItemRangeRemoved(0, size);
            }
        }

        public void remove(int position) {

            dbManager.removeShoppingPlanner(getActivity(),mItems.get(position));
            mItems.remove(position);
            notifyItemRemoved(position);

        }





    }


    public class MovieTouchHelper extends ItemTouchHelper.SimpleCallback {
        private ShoppingPlannerItemAdapter shoppingPlannerItemAdapter;

        public MovieTouchHelper(ShoppingPlannerItemAdapter shoppingPlannerItemAdapter){
            super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            this.shoppingPlannerItemAdapter = shoppingPlannerItemAdapter;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //TODO: Not implemented here
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //Remove item
            shoppingPlannerItemAdapter.remove(viewHolder.getAdapterPosition());
        }
    }


    }
