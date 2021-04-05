package com.example.avjindersinghsekhon.minimaltodo.Main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.avjindersinghsekhon.minimaltodo.AddToDo.AddToDoActivity;
import com.example.avjindersinghsekhon.minimaltodo.AddToDo.AddToDoFragment;
import com.example.avjindersinghsekhon.minimaltodo.Analytics.AnalyticsApplication;
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
import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class CategoryViewFragment extends AppDefaultFragment {

    private ArrayList<TaskItem> tasks;
    private RecyclerViewEmptySupport mRecyclerView;
    private CategoryItem currentCategory;
    private StoreRetrieveData storeRetrieveData;
    private CategoryViewFragment.BasicListAdapter adapter;
    public ItemTouchHelper itemTouchHelper;
    private CustomRecyclerScrollViewListener customRecyclerScrollViewListener;
    private Drawable backArrow;
    private Toolbar toolbar;

    private static final int REQUEST_ID_TODO_ITEM = 100;

    public static final String FILENAME = "todoitems.json";

    /*Used for deletion*/
    private AnalyticsApplication app;
    private TaskItem mJustDeletedToDoItem;
    private int mIndexOfDeletedToDoItem;
    private CoordinatorLayout mCoordLayout;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);

        // get the task arraylist from mainfragment
        tasks = (ArrayList<TaskItem>) getActivity().getIntent().getSerializableExtra("tasks");

        // get the category that the user clicked
        currentCategory = (CategoryItem) getActivity().getIntent().getSerializableExtra("categoryClicked");
        String theme = getActivity().getSharedPreferences(MainFragment.THEME_PREFERENCES, MODE_PRIVATE).getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME);
        mRecyclerView = (RecyclerViewEmptySupport) view.findViewById(R.id.toDoRecyclerView);
        if (theme.equals(MainFragment.LIGHTTHEME)) {
            mRecyclerView.setBackgroundColor(getResources().getColor(R.color.primary_lightest));
        } else if (theme.equals(MainFragment.LIGHTREDTHEME)) {
            mRecyclerView.setBackgroundColor(getResources().getColor(R.color.primary_lightest));
        } else if (theme.equals(MainFragment.LIGHTYELLOWTHEME)) {
            mRecyclerView.setBackgroundColor(getResources().getColor(R.color.primary_lightest));
        } else if (theme.equals(MainFragment.LIGHTGREENTHEME)) {
            mRecyclerView.setBackgroundColor(getResources().getColor(R.color.primary_lightest));
        } else if (theme.equals(MainFragment.LIGHTBLUETHEME)) {
            mRecyclerView.setBackgroundColor(getResources().getColor(R.color.primary_lightest));
        } else if (theme.equals(MainFragment.LIGHTPINKTHEME)) {
            mRecyclerView.setBackgroundColor(getResources().getColor(R.color.primary_lightest));
        }

        // setup the recycler view
        mRecyclerView.setEmptyView(view.findViewById(R.id.toDoEmptyView));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CategoryViewFragment.BasicListAdapter(tasks);
        storeRetrieveData = new StoreRetrieveData(getContext(), FILENAME);

        mCoordLayout = (CoordinatorLayout) view.findViewById(R.id.myCoordinatorLayout);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        // setup a listener for the back arrow to go back to the main page
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //let main fragment know we need to recreate the page
                MainFragment.navBack = true;

                // create a new intent and put the updated arraylist of tasks in it
                Intent i = new Intent();
                i.putExtra("newArray",tasks);

                try {
                    storeRetrieveData.saveToFile(tasks);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

                // set the result to ok and finish the activity
                getActivity().setResult(RESULT_OK,i);
                getActivity().finish();

            }
        });

        // setup a listener for the recycler scroll view
        customRecyclerScrollViewListener = new CustomRecyclerScrollViewListener() {
            @Override
            public void show() { }

            @Override
            public void hide() { }
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

        /**
         * Responsible for moving the position of items
         * @param fromPosition
         * @param toPosition
         */
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

        /**
         * Responsible for deleting items
         * @param position
         */
        @Override
        public void onItemRemoved(final int position) {
            app = (AnalyticsApplication) getActivity().getApplication();
            app.send(this, "Action", "Swiped Todo Away");

            //remove the task and store it so that if the user wants to undo it can be retrieved
            mJustDeletedToDoItem = items.remove(position);
            //store the position
            mIndexOfDeletedToDoItem = position;
            Intent i = new Intent(getContext(), TodoNotificationService.class);
            //remove the alarm
            deleteAlarm(i, mJustDeletedToDoItem.getIdentifier().hashCode());
            notifyItemRemoved(position);

            //display snackbar undo button
            String toShow = "Todo";
            Snackbar.make(mCoordLayout, "Deleted " + toShow, Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //put the task back
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
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MainFragment.THEME_PREFERENCES, MODE_PRIVATE);
            //Background color for each to-do item. Necessary for night/day mode
            int bgColor;
            //color of title text in our to-do item. White for night mode, dark gray for day mode
            int todoTextColor;

            if (sharedPreferences.getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME).equals(MainFragment.LIGHTTHEME)) {
                bgColor = Color.WHITE;
                todoTextColor = getResources().getColor(R.color.secondary_text);
            } else if (sharedPreferences.getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME).equals(MainFragment.LIGHTREDTHEME)) {
                bgColor = Color.WHITE;
                todoTextColor = getResources().getColor(R.color.secondary_text);
            } else if (sharedPreferences.getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME).equals(MainFragment.LIGHTYELLOWTHEME)) {
                bgColor = Color.WHITE;
                todoTextColor = getResources().getColor(R.color.secondary_text);
            } else if (sharedPreferences.getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME).equals(MainFragment.LIGHTGREENTHEME)) {
                bgColor = Color.WHITE;
                todoTextColor = getResources().getColor(R.color.secondary_text);
            } else if (sharedPreferences.getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME).equals(MainFragment.LIGHTBLUETHEME)) {
                bgColor = Color.WHITE;
                todoTextColor = getResources().getColor(R.color.secondary_text);
            } else if (sharedPreferences.getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME).equals(MainFragment.LIGHTPINKTHEME)) {
                bgColor = Color.WHITE;
                todoTextColor = getResources().getColor(R.color.secondary_text);
            } else {
                bgColor = getResources().getColor(R.color.darkModeTasks);
                todoTextColor = Color.WHITE;
            }
            holder.linearLayout.setBackgroundColor(bgColor);

            /**
             * If the task is a todoitem and belongs to the category, then display it
             */
            if(item instanceof ToDoItem && ((ToDoItem) item).getCategoryBelongs().equalsIgnoreCase(currentCategory.getTitle()))
            {

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
                holder.mToDoTextview.setTextColor(todoTextColor);
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

            /**
             * Else the task doesn't belong to the current category, dont display it
             */
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

    private boolean doesPendingIntentExist(Intent i, int requestCode) {
        PendingIntent pi = PendingIntent.getService(getContext(), requestCode, i, PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }

    private AlarmManager getAlarmManager() {
        return (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
    }

    private void deleteAlarm(Intent i, int requestCode) {
        if (doesPendingIntentExist(i, requestCode)) {
            PendingIntent pi = PendingIntent.getService(getContext(), requestCode, i, PendingIntent.FLAG_NO_CREATE);
            pi.cancel();
            getAlarmManager().cancel(pi);
            Log.d("OskarSchindler", "PI Cancelled " + doesPendingIntentExist(i, requestCode));
        }
    }

    private void createAlarm(Intent i, int requestCode, long timeInMillis) {
        AlarmManager am = getAlarmManager();
        PendingIntent pi = PendingIntent.getService(getContext(), requestCode, i, PendingIntent.FLAG_UPDATE_CURRENT);
        am.set(AlarmManager.RTC_WAKEUP, timeInMillis, pi);
//        Log.d("OskarSchindler", "createAlarm "+requestCode+" time: "+timeInMillis+" PI "+pi.toString());
    }



}
