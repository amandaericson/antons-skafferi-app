package se.grupp1.antonsskafferi.popups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import se.grupp1.antonsskafferi.lib.DatabaseURL;
import se.grupp1.antonsskafferi.lib.HttpRequest;
import se.grupp1.antonsskafferi.components.MenuComponent;
import se.grupp1.antonsskafferi.R;
import se.grupp1.antonsskafferi.data.OrderItemData;

public class OrderSummaryPopup extends DialogFragment {

    private ArrayList<OrderItemData> itemData = new ArrayList<>();

    private int tableId;

    public OrderSummaryPopup(int tableId)
    {
        this.tableId = tableId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.popup_order_summary, container, false);

        //getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_bg);

        LinearLayout list = v.findViewById(R.id.summaryItemsList);

        for(int i = 0; i < itemData.size(); i++)
        {
            OrderItemData order = itemData.get(i);

            list.addView(new MenuComponent(this.getContext(), order));
        }

        v.findViewById(R.id.closeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        v.findViewById(R.id.popupOrderButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postOrder();
                dismiss();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes(params);
    }

    public void setItemData(ArrayList<OrderItemData> itemData)
    {
        this.itemData = itemData;
    }

    private void postOrder()
    {
        for(int i = 0; i < itemData.size(); i++)
        {
            OrderItemData itemData = this.itemData.get(i);

            JSONObject object = new JSONObject();

            try
            {
                object.put("dinnertable", tableId);

                int itemId = itemData.getId();

                System.out.println(itemId);

                object.put("item", itemId);
                object.put("amount", itemData.getAmount());
                object.put("note", itemData.getNote());
                object.put("ready", 0);

                HttpRequest.Response response = new HttpRequest.Response() {
                    @Override
                    public void processFinish(String output, int status) {
                        System.out.println(output);
                    }
                };

                HttpRequest httpRequest = new HttpRequest(response);
                httpRequest.setRequestMethod("POST");

                System.out.println(object.toString());
                httpRequest.setPayload(object.toString());
                httpRequest.execute(DatabaseURL.insertOrder);
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

}
