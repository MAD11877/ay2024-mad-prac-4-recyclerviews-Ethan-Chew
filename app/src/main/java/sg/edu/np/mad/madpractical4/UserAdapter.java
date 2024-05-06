package sg.edu.np.mad.madpractical4;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private ArrayList<User> listObjects;

    public UserAdapter(ArrayList<User> listObjects, ListActivity activity) {
        this.listObjects = listObjects;
    }

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(UserViewHolder holder, int position) {
        User listItem = listObjects.get(position);
        holder.name.setText(listItem.getName());
        holder.description.setText(listItem.getDescription());

        holder.smallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(listItem.getName());
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Profile")
                        .setMessage(listItem.getName())
                        .setCancelable(true)
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Random rand = new Random();
                                int randomNum = rand.nextInt(999999);
                                // Send Random Number to MainActivity as an Intent
                                Intent sendUser = new Intent(v.getContext(), MainActivity.class);
                                sendUser.putExtra("name", listItem.getName());
                                sendUser.putExtra("description", listItem.getDescription());
                                sendUser.putExtra("followed", listItem.getFollowed());
                                sendUser.putExtra("id", listItem.getId());
                                v.getContext().startActivity(sendUser);
                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alert = builder.create();
            }
        });
    }

    public int getItemCount() { return listObjects.size(); }
}
