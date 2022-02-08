package net.ivanvega.soportediferentespantallasb;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.mbms.StreamingServiceInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleFragment extends Fragment
        implements MediaPlayer.OnPreparedListener,
            MediaController.MediaPlayerControl,
        View.OnTouchListener
{
    MediaPlayer mediaPlayer;
    MediaController mediaController;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String ARG_LIBRO_POS = "posLibro";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView txtTitulo;
    private TextView txtAutor;
    private ImageView imvPortada;

    public DetalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleFragment newInstance(String param1, String param2) {
        DetalleFragment fragment = new DetalleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.detalle_fragment_layout,
                container, false);

        layout.setOnTouchListener(this);
        Spinner spinner = layout.findViewById(R.id.spnGeneros);

        String[] arrGeneros =
                getResources().getStringArray(R.array.generos);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        arrGeneros
                );

        spinner.setAdapter(adapter);


        Bundle  params =  getArguments();

        if(params!=null){
            int pos =
                    params.getInt(DetalleFragment.ARG_LIBRO_POS);
            setInfoLibro(pos, layout);
        }else{
            setInfoLibro(0, layout);
        }
        return layout;
    }

    private void setInfoLibro(int pos, View layout) {
        Libro libro = Libro.ejemplosLibros().elementAt(pos);

        txtTitulo = (TextView) layout.findViewById(R.id.titulo);
        txtAutor = (TextView) layout.findViewById(R.id.autor);
        imvPortada = (ImageView)layout.findViewById(R.id.portada);

        txtTitulo.setText(libro.getTitulo());
        txtAutor.setText(libro.getAutor());
        imvPortada.setImageResource(libro.getRecursoImagen());



        if ( mediaPlayer!=null ){
            mediaPlayer.release();
        }
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener(this);

            try {
                mediaPlayer.setDataSource(getActivity(), Uri.parse(libro.getUrl()));
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }



    }

    public void setInfoLibro(int posLibroSelectd) {
        this.setInfoLibro(posLibroSelectd, getView());
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d("Audiolibros", "Entramos en onPrepared de MediaPlayer");
        mediaController = new MediaController(getActivity());
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(
                getView().findViewById(R.id.detalle_fragment_layout_root)
        );
        mediaController.show();
        mediaPlayer.start();

    }

    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public int getDuration() {

        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {

        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int i) {
        mediaPlayer.seekTo(i);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {

        return (getCurrentPosition()/getDuration())*100;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(mediaController!=null){
            mediaController.show();
        }
        return false;
    }

    @Override
    public void onStop() {
        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onStop();
    }
}