package com.example.segundoauqui.navegationapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class Camera extends Fragment {

    private static final int CAMERA_REQUEST = 2;
    private static final String TAG = "camara";
    ImageView ivImage;
    Button btnCamera;
    Bitmap picture;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("Camera");


        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        ivImage = (ImageView) view.findViewById(R.id.ivImage);
        btnCamera = (Button) view.findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);

            }
        });








        return view ;

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST && resultCode == getActivity().RESULT_OK){
            picture = (Bitmap) data.getExtras().get("data");
            ivImage.setImageBitmap(picture);

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("bitmap", picture);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            picture = savedInstanceState.getParcelable("bitmap");
            ivImage.setImageBitmap(picture);
        }

        }


    public interface OnFragmentInteractionListener {
    }
}

