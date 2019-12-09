package se.grupp1.antonsskafferi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import se.grupp1.antonsskafferi.R;
import se.grupp1.antonsskafferi.components.BookingCardComponent;
import se.grupp1.antonsskafferi.lib.DatabaseURL;
import se.grupp1.antonsskafferi.lib.HttpRequest;

public class ViewBookingsFragment extends Fragment {

    public interface LoadingCallback
    {
        void finishedLoading();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View root = inflater.inflate(R.layout.fragment_view_bookings, container, false);

        final SwipeRefreshLayout swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                loadBookings(new LoadingCallback() {
                    @Override
                    public void finishedLoading()
                    {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        loadBookings(new LoadingCallback() {
            @Override
            public void finishedLoading() {

            }
        });
    }

    private void loadBookings(final LoadingCallback callback)
    {
        ((LinearLayout)getView().findViewById(R.id.bookingList)).removeAllViews();

        HttpRequest.Response response = new HttpRequest.Response()
        {
            @Override
            public void processFinish(String output, int status)
            {
                System.out.println(status);

                if(status != 200)
                {
                    Toast.makeText(getActivity(), "Kunde inte hämta bokningar. Felkod: " + status,
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                try {
                JSONArray jsonArr = new JSONArray(output);

                for (int i = 0; i < jsonArr.length(); i++) {
                    JSONObject obj = jsonArr.getJSONObject(i);

                    int tableid = obj.getJSONObject("dinnertable").getInt("dinnertableid");
                    String name = obj.getString("firstname");
                    int bookingamount = obj.getInt("sizeofcompany");
                    String bookingtime = obj.getString("bookingtime");


                    BookingCardComponent booking = new BookingCardComponent(getContext(), tableid, name, bookingamount, bookingtime);

                    ((LinearLayout)getView().findViewById(R.id.bookingList)).addView(booking);
                }
            }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    callback.finishedLoading();
                }
            }
        };

        HttpRequest httpRequest = new HttpRequest(response);
        httpRequest.setRequestMethod("GET");

        httpRequest.execute(DatabaseURL.getCustomers);
    }
}
