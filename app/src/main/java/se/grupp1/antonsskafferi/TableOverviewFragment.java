package se.grupp1.antonsskafferi;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TableOverviewFragment extends Fragment {

    DialogFragment popup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_table_overview, container, false);

        root.findViewById(R.id.addBookingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getView());
                navController.navigate(R.id.navigation_booking);
            }
        });

        root.findViewById(R.id.tableId1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.addToBackStack(null);

                popup = new BookedTablePopupFragment();

                popup.show(getChildFragmentManager(), "popup");
            }
        });

        root.findViewById(R.id.tableId2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.addToBackStack(null);

                popup = new UnbookedTablePopupFragment();

                popup.show(getChildFragmentManager(), "popup");
            }
        });

        return root;
    }


    public void newOrder()
    {
        popup.dismiss();

        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.navigation_new_order);
    }

    public void setTable2Booked()
    {
        CardView cardView = getView().findViewById(R.id.table2Card);

        cardView.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.bookedTableColor));
    }

}
