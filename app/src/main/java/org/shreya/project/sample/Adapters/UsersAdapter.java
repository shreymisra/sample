package org.shreya.project.sample.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.shreya.project.sample.Activities.MainActivity;
import org.shreya.project.sample.ImageTransform;
import org.shreya.project.sample.R;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.view_holder> {
    Context context;
    public UsersAdapter(Context context)
    {
        this.context=context;
    }
    @NonNull
    @Override
    public UsersAdapter.view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_card,parent,false);

        return new view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.view_holder holder, int position) {
         try{
             Picasso
                     .with(context)
                     .load("https://c1.staticflickr.com/3/2452/3985922948_360953da17.jpg")
                     .placeholder(R.drawable.ic_account_circle_white_24dp)
                     .error(R.drawable.ic_account_circle_white_24dp)
                     .transform(new ImageTransform())
                     .into(holder.profile);
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
         holder.name.setText("Shrey Misra");
         holder.email.setText("shreymisra8@gmail.com");
         holder.mob.setText("9451119911");
         holder.call.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (ContextCompat.checkSelfPermission(context,
                         Manifest.permission.CALL_PHONE)
                         != PackageManager.PERMISSION_GRANTED) {

                     ActivityCompat.requestPermissions(((Activity) context),
                             new String[]{Manifest.permission.CALL_PHONE},
                             1000);
                 } else {
                     Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9458210305"));
                     context.startActivity(intent);
                 }

             }

         });

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    public class view_holder extends RecyclerView.ViewHolder{

        ImageView profile,call;
        TextView name,email,mob;

        private view_holder(View itemView) {
            super(itemView);
            profile=itemView.findViewById(R.id.profilePic);
            call=itemView.findViewById(R.id.call);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            mob=itemView.findViewById(R.id.mob);
        }
    }
}
