package se.grupp1.antonsskafferi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import se.grupp1.antonsskafferi.R;

public class AddEventPicturesFragment extends Fragment
{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_add_event_pictures, container, false);

        return root;
    }
}
