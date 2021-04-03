package com.example.avjindersinghsekhon.minimaltodo.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.avjindersinghsekhon.minimaltodo.AddToDo.AddToDoActivity;
import com.example.avjindersinghsekhon.minimaltodo.AddToDo.AddToDoFragment;
import com.example.avjindersinghsekhon.minimaltodo.R;


import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultFragment;
import com.example.avjindersinghsekhon.minimaltodo.Utility.CategoryItem;
import com.example.avjindersinghsekhon.minimaltodo.Utility.ItemTouchHelperClass;
import com.example.avjindersinghsekhon.minimaltodo.Utility.RecyclerViewEmptySupport;
import com.example.avjindersinghsekhon.minimaltodo.Utility.StoreRetrieveData;
import com.example.avjindersinghsekhon.minimaltodo.Utility.TaskItem;
import com.example.avjindersinghsekhon.minimaltodo.Utility.ToDoItem;
import com.example.avjindersinghsekhon.minimaltodo.Utility.TodoNotificationService;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class CategoryViewFragment extends AppDefaultFragment {

    private ArrayList<TaskItem> tasks;
    private FloatingActionButton backButton;
    private RecyclerViewEmptySupport mRecyclerView;
    private CategoryItem currentCategory;
    private StoreRetrieveData storeRetrieveData;
    private CategoryViewFragment.BasicListAdapter adapter;
    public ItemTouchHelper itemTouchHelper;
    private CustomRecyclerScrollViewListener customRecyclerScrollViewListener;

    private static final int REQUEST_ID_TODO_ITEM = 100;

    public static final String FILENAME = "todoitems.json";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
        tasks = (ArrayList<TaskItem>) getActivity().getIntent().getSerializableExtra("tasks");
        currentCategory = (CategoryItem) getActivity().getIntent().getSerializableExtra("categoryClicked");

        backButton = (FloatingActionButton) view.findViewById(R.id.backButton);

        mRecyclerView = (RecyclerViewEmptySupport) view.findViewById(R.id.toDoRecyclerView);
        mRecyclerView.setEmptyView(view.findViewById(R.id.toDoEmptyView));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CategoryViewFragment.BasicListAdapter(tasks);
        storeRetrieveData = new StoreRetrieveData(getContext(), FILENAME);

        backButton.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.putExtra("newArray",tasks);

                try {
                    storeRetrieveData.saveToFile(tasks);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

                getActivity().setResult(RESULT_OK,i);
                getActivity().finish();
            }
        });

        customRecyclerScrollViewListener = new CustomRecyclerScrollViewListener() {
            @Override
            public void show() {

               // mAddToDoItemFAB.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
//                mAddToDoItemFAB.animate().translationY(0).setInterpolator(new AccelerateInterpolator(2.0f)).start();
            }

            @Override
            public void hide() {
                //CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) mAddToDoItemFAB.getLayoutParams();
                //int fabMargin = lp.bottomMargin;
                //mAddToDoItemFAB.animate().translationY(mAddToDoItemFAB.getHeight() + fabMargin).setInterpolator(new AccelerateInterpolator(2.0f)).start();
            }
        };

        mRecyclerView.addOnScrollListener(customRecyclerScrollViewListener);

        ItemTouchHelper.Callback callback = new ItemTouchHelperClass(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);


        mRecyclerView.setAdapter(adapter);


    }

    public static Fragment newInstance() {
        return new CategoryViewFragment();
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_category;
    }

    public class BasicListAdapter extends RecyclerView.Adapter<CategoryViewFragment.BasicListAdapter.ViewHolder> implements ItemTouchHelperClass.ItemTouchHelperAdapter {
        private ArrayList<TaskItem> items;

        @Override
        public void onItemMoved(int fromPosition, int toPosition) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(items, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(items, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onItemRemoved(final int position) {
            //Remove this line if not using Google Analytics
            /*
            app.send(this, "Action", "Swiped Todo Away");

            mJustDeletedToDoItem = items.remove(position);
            mIndexOfDeletedToDoItem = position;
            Intent i = new Intent(getContext(), TodoNotificationService.class);
            deleteAlarm(i, mJustDeletedToDoItem.getIdentifier().hashCode());
            notifyItemRemoved(position);

//            String toShow = (mJustDeletedToDoItem.getToDoText().length()>20)?mJustDeletedToDoItem.getToDoText().substring(0, 20)+"...":mJustDeletedToDoItem.getToDoText();
            String toShow = "Todo";
            Snackbar.make(mCoordLayout, "Deleted " + toShow, Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Comment the line below if not using Google Analytics
                            app.send(this, "Action", "UNDO Pressed");
                            items.add(mIndexOfDeletedToDoItem, mJustDeletedToDoItem);
                            if (((ToDoItem) mJustDeletedToDoItem).getToDoDate() != null && ((ToDoItem) mJustDeletedToDoItem).hasReminder()) {
                                Intent i = new Intent(getContext(), TodoNotificationService.class);
                                i.putExtra(TodoNotificationService.TODOTEXT, ((ToDoItem) mJustDeletedToDoItem).getToDoText());
                                i.putExtra(TodoNotificationService.TODOUUID, mJustDeletedToDoItem.getIdentifier());
                                createAlarm(i, mJustDeletedToDoItem.getIdentifier().hashCode(), ((ToDoItem) mJustDeletedToDoItem).getToDoDate().getTime());
                            }
                            notifyItemInserted(mIndexOfDeletedToDoItem);
                        }
                    }).show();

             */
        }

        @Override
        public CategoryViewFragment.BasicListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_circle_try, parent, false);
            return new CategoryViewFragment.BasicListAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final CategoryViewFragment.BasicListAdapter.ViewHolder holder, final int position) {
            TaskItem item = items.get(position);

//            if(item.getToDoDate()!=null && item.getToDoDate().before(new Date())){
//                item.setToDoDate(null);
//            }
            //SharedPreferences sharedPreferences = getActivity().getSharedPreferences(THEME_PREFERENCES, MODE_PRIVATE);
            //Background color for each to-do item. Necessary for night/day mode
            int bgColor;
            //color of title text in our to-do item. White for night mode, dark gray for day mode
            int todoTextColor;
            /*
            if (sharedPreferences.getString(THEME_SAVED, LIGHTTHEME).equals(LIGHTTHEME)) {
                bgColor = Color.WHITE;
                todoTextColor = getResources().getColor(R.color.secondary_text);
            } else if (sharedPreferences.getString(THEME_SAVED, LIGHTTHEME).equals(LIGHTREDTHEME)) {
                bgColor = Color.WHITE;
                todoTextColor = getResources().getColor(R.color.secondary_text);
            } else if (sharedPreferences.getString(THEME_SAVED, LIGHTTHEME).equals(LIGHTYELLOWTHEME)) {
                bgColor = Color.WHITE;
                todoTextColor = getResources().getColor(R.color.secondary_text);
            } else if (sharedPreferences.getString(THEME_SAVED, LIGHTTHEME).equals(LIGHTGREENTHEME)) {
                bgColor = Color.WHITE;
                todoTextColor = getResources().getColor(R.color.secondary_text);
            } else if (sharedPreferences.getString(THEME_SAVED, LIGHTTHEME).equals(LIGHTBLUETHEME)) {
                bgColor = Color.WHITE;
                todoTextColor = getResources().getColor(R.color.secondary_text);
            } else if (sharedPreferences.getString(THEME_SAVED, LIGHTTHEME).equals(LIGHTPINKTHEME)) {
                bgColor = Color.WHITE;
                todoTextColor = getResources().getColor(R.color.secondary_text);
            } else {
                bgColor = getResources().getColor(R.color.darkModeTasks);
                todoTextColor = Color.WHITE;
            }
            holder.linearLayout.setBackgroundColor(bgColor);
            */
            if(item instanceof ToDoItem && ((ToDoItem) item).getCategoryBelongs().equalsIgnoreCase(currentCategory.getTitle()))
            {

                System.out.println("Title: " + ((ToDoItem) item).getToDoText() + "\nDesc: " + ((ToDoItem) item).getmToDoDescription() + "\nCategory: " + ((ToDoItem) item).getCategoryBelongs());

                holder.linearLayout.setVisibility(View.VISIBLE);

                if (((ToDoItem) item).hasReminder() && ((ToDoItem) item).getToDoDate() != null) {
                    holder.mToDoTextview.setMaxLines(1);
                    holder.mTimeTextView.setVisibility(View.VISIBLE);
//                holder.mToDoTextview.setVisibility(View.GONE);
                } else {
                    holder.mTimeTextView.setVisibility(View.GONE);
                    holder.mToDoTextview.setMaxLines(2);
                }

                if(((ToDoItem) item).isPriority()) {
                    holder.mToDoTextview.setText(((ToDoItem) item).getToDoText() + new String(Character.toChars(0x2757)));


                }
                else {
                    holder.mToDoTextview.setText(((ToDoItem) item).getToDoText());
                }
                //holder.mToDoTextview.setTextColor(todoTextColor);
//              holder.mColorTextView.setBackgroundColor(Color.parseColor(item.getTodoColor()));

//              TextDrawable myDrawable = TextDrawable.builder().buildRoundRect(item.getToDoText().substring(0,1),Color.RED, 10);
                //We check if holder.color is set or not
//              if(item.getTodoColor() == null){
//                  ColorGenerator generator = ColorGenerator.MATERIAL;
//                  int color = generator.getRandomColor();
//                  item.setTodoColor(color+"");
//              }
//              Log.d("OskarSchindler", "Color: "+item.getTodoColor());
                TextDrawable myDrawable = TextDrawable.builder().beginConfig()
                        .textColor(Color.WHITE)
                        .useFont(Typeface.DEFAULT)
                        .toUpperCase()
                        .endConfig()
                        .buildRound(((ToDoItem) item).getToDoText().substring(0, 1), ((ToDoItem) item).getTodoColor());

//              TextDrawable myDrawable = TextDrawable.builder().buildRound(item.getToDoText().substring(0,1),holder.color);
                holder.mColorImageView.setImageDrawable(myDrawable);
                if (((ToDoItem) item).getToDoDate() != null) {
                    String timeToShow;
                    if (android.text.format.DateFormat.is24HourFormat(getContext())) {
                        timeToShow = AddToDoFragment.formatDate(MainFragment.DATE_TIME_FORMAT_24_HOUR, ((ToDoItem) item).getToDoDate());
                    } else {
                        timeToShow = AddToDoFragment.formatDate(MainFragment.DATE_TIME_FORMAT_12_HOUR, ((ToDoItem) item).getToDoDate());
                    }
                    holder.mTimeTextView.setText(timeToShow);
                }


            }

            else{
                holder.linearLayout.setVisibility(View.GONE);
                holder.itemView.setLayoutParams(new LinearLayout.LayoutParams(0,0));
            }
            /*
            else if (item instanceof CategoryItem){
                //set category title once CategoryItem is created
                CategoryItem categoryItem;
                holder.mToDoTextview.setText(((CategoryItem) item).getTitle());
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    //When category is click show test
                    public void onClick(View view) {
                        //Toast.makeText(getContext(),"test",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getContext(),CategoryView.class);
                        //i.putExtra("tasks",mToDoItemsArrayList);




                        startActivity(i);


                    }
                });

            }

             */
        }



        @Override
        public int getItemCount() {
            return items.size();
        }

        BasicListAdapter(ArrayList<TaskItem> items) {

            this.items = items;
        }


        @SuppressWarnings("deprecation")
        public class ViewHolder extends RecyclerView.ViewHolder {
            //create image of folder icon

            View mView;
            LinearLayout linearLayout;
            TextView mToDoTextview;
            //            TextView mColorTextView;
            ImageView mColorImageView;
            TextView mTimeTextView;
//            int color = -1;

            //Brings up already created task form
            public ViewHolder(View v) {
                super(v);
                mView = v;
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TaskItem item = items.get(CategoryViewFragment.BasicListAdapter.ViewHolder.this.getAdapterPosition());

                        if(item instanceof ToDoItem){
                            Intent i = new Intent(getContext(), AddToDoActivity.class);
                            i.putExtra(MainFragment.TODOITEM, item);
                            startActivityForResult(i, REQUEST_ID_TODO_ITEM);
                        }
                        /*
                        else if(item instanceof CategoryItem){
                            Intent i = new Intent(getContext(),CustomDialogActivity.class);
                            i.putExtra("category",item);
                            startActivityForResult(i,REQUEST_ID_CAT_ITEM);
                        }
                         */
                    }
                });
                mToDoTextview = (TextView) v.findViewById(R.id.toDoListItemTextview);
                mTimeTextView = (TextView) v.findViewById(R.id.todoListItemTimeTextView);
//                mColorTextView = (TextView)v.findViewById(R.id.toDoColorTextView);
                mColorImageView = (ImageView) v.findViewById(R.id.toDoListItemColorImageView);

                linearLayout = (LinearLayout) v.findViewById(R.id.listItemLinearLayout);


            }


        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            storeRetrieveData.saveToFile(tasks);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        try {
            storeRetrieveData.saveToFile(tasks);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        mRecyclerView.removeOnScrollListener(customRecyclerScrollViewListener);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_CANCELED && requestCode == REQUEST_ID_TODO_ITEM) {

            // Retrieve new TaskItem from the Intent
            TaskItem item = (TaskItem) data.getSerializableExtra(MainFragment.TODOITEM);

            // If the TaskItem is a ToDoItem
            if (item instanceof ToDoItem)
            {
                System.out.println("The tasks category is now: " + ((ToDoItem) item).getCategoryBelongs());

                if (((ToDoItem) item).getToDoText().length() <= 0) {
                    return;
                }

                if (((ToDoItem) item).hasReminder() && ((ToDoItem) item).getToDoDate() != null) {
                    Intent i = new Intent(getContext(), TodoNotificationService.class);
                    i.putExtra(TodoNotificationService.TODOTEXT, ((ToDoItem) item).getToDoText());
                    i.putExtra(TodoNotificationService.TODOUUID, item.getIdentifier());
//                Log.d("OskarSchindler", "Alarm Created: "+item.getToDoText()+" at "+item.getToDoDate());
                }

                for (int i = 0; i < tasks.size(); i++) {
                    if (item.getIdentifier().equals(tasks.get(i).getIdentifier())) {
                        tasks.set(i, item);
                        adapter.notifyDataSetChanged();
                        break;
                    }
                }

                try {
                    storeRetrieveData.saveToFile(tasks);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }
}
