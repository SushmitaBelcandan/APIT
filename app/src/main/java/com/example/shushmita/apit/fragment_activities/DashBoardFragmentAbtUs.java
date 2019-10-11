package com.example.shushmita.apit.fragment_activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shushmita.apit.R;
import com.example.shushmita.apit.activities.SignUp_Act;
import com.example.shushmita.apit.adapters.Utils;
import com.example.shushmita.apit.retrofit_models.APIClient;
import com.example.shushmita.apit.retrofit_models.APIInterface;
import com.example.shushmita.apit.retrofit_models.AboutUsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardFragmentAbtUs extends Fragment {

    APIInterface apiInterface;
    private ProgressBar pbLoading;
    private ImageView ivBanner;
    private TextView tvAddress, tvPhone, tvEmail, tvWebsite, tvFacebook, tvLinkedIn, tvEmptyPage;
    private WebView webvDesc;
    private LinearLayout llAddress, llPhone, llEmail, llWebsite, llFacebook, llLinkedin;
    private FrameLayout flDashboard;
    private String str_Website;

    public DashBoardFragmentAbtUs() {
        //required public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.about_us_frag, container, false);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        pbLoading = view.findViewById(R.id.pbLoading);
        pbLoading.setVisibility(View.VISIBLE);

        ivBanner = view.findViewById(R.id.ivBanner);
        tvAddress = view.findViewById(R.id.tvAddress);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvWebsite = view.findViewById(R.id.tvWebsite);
        webvDesc = view.findViewById(R.id.webvDesc);
        tvFacebook = view.findViewById(R.id.tvFacebook);
        tvLinkedIn = view.findViewById(R.id.tvLinkedIn);
        tvEmptyPage = view.findViewById(R.id.tvEmptyPage);
        flDashboard = view.findViewById(R.id.flDashboard);

        llAddress = view.findViewById(R.id.llAddress);
        llPhone = view.findViewById(R.id.llPhone);
        llEmail = view.findViewById(R.id.llEmail);
        llWebsite = view.findViewById(R.id.llWebsite);
        llFacebook = view.findViewById(R.id.llFacebook);
        llLinkedin = view.findViewById(R.id.llLinkedin);
        getDashboardData();
        return view;

    }

    public void getDashboardData() {
        if (Utils.CheckInternetConnection(getContext())) {

            final Call<AboutUsModel> abtusCall = apiInterface.getAboutusData();
            abtusCall.enqueue(new Callback<AboutUsModel>() {
                @Override
                public void onResponse(Call<AboutUsModel> call, Response<AboutUsModel> response) {

                    final AboutUsModel abtusResources = response.body();
                    if (abtusResources.status.equals("success")) {
                        pbLoading.setVisibility(View.INVISIBLE);
                        //banner image-------------------------------------------------
                        if (abtusResources.banner != null && !abtusResources.banner.isEmpty() &&
                                !abtusResources.banner.equals("null")) {
                            Glide.with(getContext()).load(abtusResources.banner).into(ivBanner);
                        } else {
                            Glide.with(getContext()).load(R.drawable.rice_mill).into(ivBanner);
                        }
                        //description---------------------------------------------
                        if (abtusResources.description != null && !abtusResources.description.isEmpty() &&
                                !abtusResources.description.equals("null")) {
                            webvDesc.setVisibility(View.VISIBLE);
                            webvDesc.loadData(abtusResources.description, "text/html", null);
                        } else {
                            webvDesc.setVisibility(View.GONE);
                        }
                        //Address------------------------------------------------------
                        if (abtusResources.address != null && !abtusResources.address.isEmpty() &&
                                !abtusResources.address.equals("null")) {
                            llAddress.setVisibility(View.VISIBLE);
                            tvAddress.setText(" " + abtusResources.address);
                        } else {
                            llAddress.setVisibility(View.GONE);

                        }
                        //Phone------------------------------------------------------
                        if (abtusResources.phone != null && !abtusResources.phone.isEmpty() &&
                                !abtusResources.phone.equals("null")) {
                            llPhone.setVisibility(View.VISIBLE);
                            tvPhone.setText(" " + abtusResources.phone);
                            tvPhone.setLinkTextColor(Color.parseColor("#0c53a0"));
                            tvPhone.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:"+abtusResources.phone));
                                    startActivity(intent);
                                }
                            });

                        } else {
                            llPhone.setVisibility(View.GONE);

                        }
                        //Email------------------------------------------------------
                        if (abtusResources.email != null && !abtusResources.email.isEmpty() &&
                                !abtusResources.email.equals("null")) {
                            llEmail.setVisibility(View.VISIBLE);
                            tvEmail.setText(" " + abtusResources.email);
                            tvEmail.setLinkTextColor(Color.parseColor("#0c53a0"));
                            tvEmail.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent emailIntent = new Intent(Intent.ACTION_SEND,Uri.fromParts(
                                            "mailto",abtusResources.email, null));
                                    emailIntent.setType("text/plain");
                                    startActivity(Intent.createChooser(emailIntent, "Send your email in:"));
                                }
                            });
                        } else {
                            llEmail.setVisibility(View.GONE);

                        }
                        //Website------------------------------------------------------
                        if (abtusResources.website != null && !abtusResources.website.isEmpty() &&
                                !abtusResources.website.equals("null")) {
                            llWebsite.setVisibility(View.VISIBLE);

                            str_Website = abtusResources.website;
                            tvWebsite.setMovementMethod(LinkMovementMethod.getInstance()); //make text redirect to website
                            tvWebsite.setText(Html.fromHtml(getResources().getString(R.string.website_link)));
                            tvWebsite.setLinkTextColor(Color.parseColor("#0c53a0"));
                        } else {
                            llWebsite.setVisibility(View.GONE);

                        }
                        //Facebook------------------------------------------------------
                        tvFacebook.setMovementMethod(LinkMovementMethod.getInstance()); //make text redirect to facebook page
                        tvFacebook.setText(Html.fromHtml(getResources().getString(R.string.facebook_link)));
                        tvFacebook.setLinkTextColor(Color.parseColor("#0c53a0"));
                        //LinkedIn------------------------------------------------------
                        tvLinkedIn.setMovementMethod(LinkMovementMethod.getInstance());
                        tvLinkedIn.setText(Html.fromHtml(getResources().getString(R.string.linkedin_link)));
                        tvLinkedIn.setLinkTextColor(Color.parseColor("#0c53a0"));

                    } else {
                        pbLoading.setVisibility(View.INVISIBLE);
                        tvEmptyPage.setVisibility(View.VISIBLE);
                        flDashboard.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Data Unavailable!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AboutUsModel> call, Throwable t) {
                    call.cancel();
                }
            });
        } else {
            Toast.makeText(getContext(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}
