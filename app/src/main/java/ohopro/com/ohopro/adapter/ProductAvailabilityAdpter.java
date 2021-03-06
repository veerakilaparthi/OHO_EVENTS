package ohopro.com.ohopro.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import ohopro.com.ohopro.Holder.Holder;
import ohopro.com.ohopro.R;
import ohopro.com.ohopro.domains.FoodMenuImageUrl;
import ohopro.com.ohopro.domains.ProductAvailabityRes;

/**
 * Created by home on 12/28/2017.
 */

public class ProductAvailabilityAdpter extends RecyclerView.Adapter<Holder> {

    private Context context;
    private List<ProductAvailabityRes> completedInfos;
    ProductAvailabityRes res;
    String start_id;
    String url;

    public ProductAvailabilityAdpter(Context context, List<ProductAvailabityRes> completedInfos) {
        this.context = context;
        this.completedInfos = completedInfos;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.productavilability_list, parent, false);
        return new Holder(itemView);    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {

        holder.pname.setText(completedInfos.get(position).getProductName());
        holder.group.setText(completedInfos.get(position).getProductGroup());
        holder.serviceitem.setText(completedInfos.get(position).getProductOrServiceCategory());
        holder.category.setText(completedInfos.get(position).getCategory());
        holder.status.setText(completedInfos.get(position).getStatus());
         res=completedInfos.get(position);
        if(res.getImages()==null)
        {
           // Toast.makeText(context,"no Images",Toast.LENGTH_SHORT).show();
        }
        else {
            for (int j=0;j<res.getImages().size();j++){

                Log.e("veera",String.valueOf(res.getImages().get(j).getUrl()));
                Glide.with(context)
                        .load(res.getImages().get(j).getUrl())
                        .crossFade()
                        .thumbnail(0.5f)
                        .error(R.mipmap.logo)      // optional
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.pimage);
            }
        }
        if(res.getFoodMenuImageUrl()==null)
        {
           // Toast.makeText(context,"no Images",Toast.LENGTH_SHORT).show();
        }
        else {
            for (int j=0;j<res.getFoodMenuImageUrl().size();j++){
                Log.e("veera",String.valueOf(res.getFoodMenuImageUrl().get(j).getUrl()));
                Glide.with(context)
                        .load(res.getFoodMenuImageUrl().get(j).getUrl())
                        .crossFade()
                        .thumbnail(0.5f)
                        .error(R.mipmap.logo)      // optional
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.pimage);

                Log.e("veera",String.valueOf(res.getFoodMenuImageUrl().size()));
            }
        }




     /*  for (int j=0;j<res.getImages().size();j++){

            Log.e("veera",String.valueOf(res.getImages().get(j).getUrl()));
            Glide.with(context)
                    .load(res.getImages().get(j).getUrl())
                    .crossFade()
                    .thumbnail(0.5f)
                    .error(R.mipmap.logo)      // optional
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.pimage);
            Log.e("veera",String.valueOf(res.getImages().size()));
        }*/


    }

    @Override
    public int getItemCount() {

        return completedInfos.size();
    }
}
