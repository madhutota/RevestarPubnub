package com.sparity.revestarpubnub.app.views;

/*import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dev.homeframe.R;
import com.dev.homeframe.activities.BaseActivity;
import com.dev.homeframe.activities.DashBoardActivity;
import com.dev.homeframe.activities.PostFrameActivity;
import com.dev.homeframe.fragment.FeedsDetailFragment;
import com.dev.homeframe.fragment.FeedsLikeAndCommentsFragment;
import com.dev.homeframe.fragment.OtherUserFeedsFragment;
import com.dev.homeframe.model.FeedsListModel;
import com.dev.homeframe.model.FrameLikeComment;
import com.dev.homeframe.model.ImageModel;
import com.dev.homeframe.model.TagUserModel;
import com.dev.homeframe.services.ApiConfiguration;
import com.dev.homeframe.services.InputParams;
import com.dev.homeframe.services.JSONResult;
import com.dev.homeframe.services.JSONTask;
import com.dev.homeframe.services.NetworkUtils;
import com.dev.homeframe.utils.AppConstants;
import com.dev.homeframe.utils.AppLog;
import com.dev.homeframe.utils.ColorUtils;
import com.dev.homeframe.utils.Configuration;
import com.dev.homeframe.utils.CustomDialog;
import com.dev.homeframe.utils.DateUtils;
import com.dev.homeframe.utils.FontFamily;
import com.dev.homeframe.utils.ImageUtility;
import com.dev.homeframe.utils.Preference;
import com.dev.homeframe.utils.ShareContent;
import com.dev.homeframe.utils.TextUtils;
import com.dev.homeframe.utils.UImageLoader;
import com.dev.homeframe.utils.Utils;
import com.dev.homeframe.validation.Validations;
import com.dev.homeframe.views.CommunityFeedsView;
import com.dev.homeframe.views.HFRecyclerViewAdapter;
import com.dev.homeframe.views.UserFeedsView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;*/

/**
 * Created by Madhu
 */

/*
public class FeedsListAdapter extends HFRecyclerViewAdapter<FeedsListModel,
        FeedsListAdapter.DataViewHolder> implements View.OnClickListener, JSONResult {


    private DashBoardActivity parent;
    private JSONTask postLikeTask;
    private JSONTask deleteLikeTask;
    */
/*BLOCKED USER TASK*//*

    private JSONTask blockUserTask;

    private JSONTask deleteFrameTask;
    private JSONTask reportFrameTask;


    private ArrayList<FeedsListModel> feedsList;
    private FeedsListModel feedsListModel;

    private String FEEDS_TYPE;
    private ShareContent shareContent;
    private String screenFrom;
    private MediaController mediaControls;
    private int deletePosition;
    private View view;
    private LayoutInflater mLayoutInflater;
    private String typeOfCommunity;

    private ImageModel mImageModel;

    private String UpdatedUserId;
    private JSONTask followUserTask;

    private TextView tv_f_user_follow;


    */
/*DIALOG *//*

    private Dialog dialogMore;
    private String communityId;
    private int mlikePostion;


    public FeedsListAdapter(DashBoardActivity context, ArrayList<FeedsListModel> mList,
                            String feedsType, String from, String typeOfCommunity, String communityId) {
        super(context);
        parent = context;
        this.FEEDS_TYPE = feedsType;
        this.feedsList = mList;
        this.communityId = communityId;
        mLayoutInflater = (LayoutInflater) parent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        screenFrom = from;
        this.typeOfCommunity = typeOfCommunity;

        if (mediaControls == null) {
            mediaControls = new MediaController(context);
        }

        setData(feedsList);
    }

    @Override
    public void footerOnVisibleItem() {

    }


    @Override
    public DataViewHolder onCreateDataItemViewHolder(ViewGroup parent, int viewType) {
        view = mLayoutInflater.inflate(R.layout.row_item_list_feeds, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindDataItemViewHolder(final DataViewHolder holder, final int position) {

        feedsListModel = feedsList.get(position);
        feedsListModel.setSelectedPos(position);

        */
/*USER IMAGE*//*

        UImageLoader.setCirculerImage(holder.iv_f_user_pic, feedsListModel.getFeed_user_images().getMedium(),
                null, R.drawable.ic_user_ph);
        holder.iv_f_user_pic.setTag(feedsListModel.getFeed_user_id());

        */
/*USER FIRST AND LAST NAME*//*

        String name = FontFamily.setServerText(feedsListModel.getFeed_user_fname())
                + " " + FontFamily.setServerText(feedsListModel.getFeed_user_lname());
        holder.tv_f_user_f_l.setText(name);
        holder.tv_f_user_f_l.setTag(feedsListModel.getFeed_user_id());

        holder.ll_user_name_and_time.setTag(feedsListModel.getFeed_user_id());


        */
/*FRAME TIME STAMP*//*

        holder.tv_f_time_stamp.setText(DateUtils.getFramePostedTime(feedsListModel.getFeed_created_date()));
        holder.tv_f_time_stamp.setTag(feedsListModel.getFeed_user_id());

        */
/*FRAME POST TEXT*//*


        if (feedsListModel.getFeed_text().length() == 0 && (feedsListModel.getFeed_tag_location_name() != null && feedsListModel.getFeed_tag_location_name().equalsIgnoreCase(""))) {
            holder.tv_f_post.setVisibility(View.GONE);
        } else {
            holder.tv_f_post.setVisibility(View.VISIBLE);
            if (!Utils.isValueNullOrEmpty(feedsListModel.getFeed_text()))
                setFrameText(holder.tv_f_post, feedsListModel);

        }
        if (!Utils.isValueNullOrEmpty(feedsListModel.getFeed_text()))
            setFrameText(holder.tv_f_post, feedsListModel);


        */
/*FALLOW AND FALLOWING AND INVITE BUTTON BUTTON*//*

        final String feed_user_id = FontFamily.setServerText(feedsListModel.getFeed_user_id());
        String userId = Preference.getPrefStringData(parent, Preference.KEY_PREF_USER_ID);
        String fallow = FontFamily.setServerText(feedsListModel.getFeed_user_fallow());
        holder.tv_f_user_follow.setTag(fallow);

        if (fallow != null && !feed_user_id.equalsIgnoreCase(userId)) {

            if (FEEDS_TYPE.equals(AppConstants.FEEDS_TYPE_COMMUNITY)
                    || FEEDS_TYPE.equals(AppConstants.USER_TYPE_HASH_TAG)
                    || FEEDS_TYPE.equals(AppConstants.COMMUNITY_TYPE_TOP_HUNDRED)) {
                switch (fallow) {
                    case "unfollow":
                    case "":
                        holder.tv_f_user_follow.setVisibility(View.VISIBLE);
                        holder.tv_f_user_follow.setText(Utils.getStrings(parent, R.string.txt_follow));
                        holder.tv_f_user_follow.setTextColor(Utils.getColor(parent, R.color.colorPrimary));
                        holder.tv_f_user_follow.setBackground(Utils.getDrawable(parent, R.drawable.bg_btn_fallow));

                        holder.tv_f_user_follow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tv_f_user_follow = (TextView) v;
                                String follow = (String) v.getTag();

                                FeedsListModel mFeedsListModel = feedsList.get(position);
                                if (follow != null) {
                                    String fallow = mFeedsListModel.getFeed_user_fallow();
                                    UpdatedUserId = mFeedsListModel.getFeed_user_id();
                                    sendFollowingUserStatus(fallow, UpdatedUserId);
                                }
                            }

                        });
                        holder.tv_f_user_follow.setClickable(true);
                        break;
                    case Configuration.INVITED:
                        holder.tv_f_user_follow.setVisibility(View.VISIBLE);
                        holder.tv_f_user_follow.setText(Utils.getStrings(parent, R.string.txt_invite_plus));
                        holder.tv_f_user_follow.setTextColor(Utils.getColor(parent, R.color.colorPrimary));
                        holder.tv_f_user_follow.setBackground(Utils.getDrawable(parent, R.drawable.bg_btn_fallow));
                        holder.tv_f_user_follow.setClickable(false);
                        break;
                    default:
                        holder.tv_f_user_follow.setVisibility(View.GONE);
                        break;
                }
            }
        } else
            holder.tv_f_user_follow.setVisibility(View.GONE);


        */
/*USER TAG PICTURE*//*

        ArrayList<TagUserModel> userTagList = feedsListModel.getFeed_user_taglist();
        int userTagCount = userTagList.size();
        switch (userTagCount) {
            case 0:
                holder.iv_f_user_tag_1.setVisibility(View.GONE);
                holder.iv_f_user_tag_2.setVisibility(View.GONE);
                holder.tv_f_user_tag_count.setVisibility(View.GONE);
                break;
            case 1:
                holder.iv_f_user_tag_1.setVisibility(View.VISIBLE);
                holder.iv_f_user_tag_2.setVisibility(View.GONE);
                holder.tv_f_user_tag_count.setVisibility(View.GONE);
                break;
            case 2:
                holder.iv_f_user_tag_1.setVisibility(View.VISIBLE);
                holder.iv_f_user_tag_2.setVisibility(View.VISIBLE);
                holder.tv_f_user_tag_count.setVisibility(View.GONE);
                break;
            default:
                holder.iv_f_user_tag_1.setVisibility(View.VISIBLE);
                holder.iv_f_user_tag_2.setVisibility(View.VISIBLE);
                holder.tv_f_user_tag_count.setVisibility(View.VISIBLE);
                int total = userTagCount - 2;
                holder.tv_f_user_tag_count.setText("+" + total);
                break;
        }
        if (userTagCount > 0) {
            for (int i = 0; i < userTagList.size(); i++) {
                TagUserModel tModel = userTagList.get(i);
                if (i == 0) {
                    holder.iv_f_user_tag_1.setTag(tModel.getTag_user_id());
                    UImageLoader.setCirculerImage(holder.iv_f_user_tag_1, tModel.getTag_user_image()
                            .getMedium(), null, R.drawable.ic_user_ph);
                }
                if (i == 1) {
                    holder.iv_f_user_tag_2.setTag(tModel.getTag_user_id());
                    UImageLoader.setCirculerImage(holder.iv_f_user_tag_2, tModel.getTag_user_image()
                            .getMedium(), null, R.drawable.ic_user_ph);
                }
            }
        }

        */
/*LIKE*//*

        final String likeCount = FontFamily.setServerText(feedsListModel.getFeed_like_count());
        holder.tv_f_like_count.setText(likeCount);
        holder.tv_f_like_label.setText(FontFamily.getHeartLabel(parent, likeCount));

        */
/*COMMENTS*//*

        String commentCount = FontFamily.setServerText(feedsListModel.getFeed_comment_count());
        holder.tv_f_comment_count.setText(commentCount);
        holder.tv_f_comment_label.setText(FontFamily.getCommentLabel(parent, commentCount));
        */
/*FRAME IMAGE*//*

        if (feedsListModel.getFeed_images() != null) {

            String mediaType = feedsListModel.getFeed_images().getType();
            String imagePath = feedsListModel.getFeed_images().getOriginal();

            FrameLayout.LayoutParams params = ImageUtility.getFrameParams(parent,
                    feedsListModel.getFeed_images());

            switch (mediaType) {
                case "image":

                    holder.iv_icon_f_video.setVisibility(View.GONE);
                    holder.texture_video_view_post.setVisibility(View.GONE);
                    // holder.texture_video_view_post.pause();
                    holder.iv_thumb_video.setVisibility(View.GONE);

                    holder.iv_f_post.setLayoutParams(params);
                    holder.iv_f_post.setBackgroundColor(Utils.getColor(parent, R.color.image_hint));
                    holder.iv_f_post.setVisibility(View.VISIBLE);
                    UImageLoader.setFeedsFrameImage(holder.iv_f_post, imagePath, holder.pb_f_post,
                            R.drawable.ic_user_ph);
                    break;
                case "video":

                    holder.iv_icon_f_video.setVisibility(View.VISIBLE);
                    holder.iv_f_post.setVisibility(View.GONE);

                    holder.iv_thumb_video.setLayoutParams(params);
                    holder.iv_thumb_video.setVisibility(View.VISIBLE);
                    holder.iv_thumb_video.setBackgroundColor(Utils.getColor(parent, R.color.image_hint));
                    String thumbnail = FontFamily.setServerText(feedsListModel.getFeed_images().getThumbnail());
                    UImageLoader.setFeedsFrameImage(holder.iv_thumb_video, thumbnail, holder.pb_f_post,
                            R.drawable.ic_user_ph);

                      */
/*SET THE THUMBNAIL TO THE VIDEO VIEW *//*

                    holder.texture_video_view_post.setVisibility(View.VISIBLE);
                    */
/*TEXTURE VIDEO VIEW WIDTH AND HEIGHT PROGRAMATICALL *//*

                    // holder.texture_video_view_post.getSurfaceTexture().setDefaultBufferSize(params.width, params.height);
                    holder.texture_video_view_post.setLayoutParams(params);

                    if (!Utils.isNougatOS())
                        holder.texture_video_view_post.setBackgroundColor(Utils.getColor(parent,
                                R.color.transparent));
                    try {
                        holder.texture_video_view_post.setVideoURI(Uri.parse(imagePath));
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                    holder.texture_video_view_post.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {

                            holder.texture_video_view_post.setVisibility(View.VISIBLE);
                            holder.texture_video_view_post.start();

                            holder.iv_thumb_video.setVisibility(View.GONE);
                            holder.iv_icon_f_video.setVisibility(View.GONE);

                            try {
                                mp.setLooping(true);
                                mp.setVolume(0, 0);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    });
                    holder.texture_video_view_post.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.setLooping(false);
                            holder.iv_thumb_video.setVisibility(View.GONE);
                            holder.iv_icon_f_video.setVisibility(View.GONE);

                        }
                    });

                    holder.texture_video_view_post.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            return true;
                        }
                    });
                    break;
                default:
                    holder.iv_icon_f_video.setVisibility(View.GONE);
                    holder.iv_f_post.setVisibility(View.GONE);
                    holder.texture_video_view_post.setVisibility(View.GONE);
                    holder.iv_thumb_video.setVisibility(View.GONE);
                    break;
            }
        }
        */
/*LIKED OR UNLIKE STATUS *//*

        if (feedsListModel.getFeed_liked().equals("")) {
            feedsList.get(position).setLiked(false);
            holder.tv_icon_f_like.setText(Utils.getStrings(parent, R.string.icon_user_feeds_dislike));
            holder.tv_icon_f_like.setTextColor(Utils.getColor(parent, R.color.feeds_icon_color));
        } else {
            feedsList.get(position).setLiked(true);
            holder.tv_icon_f_like.setText(Utils.getStrings(parent, R.string.icon_user_feeds_like));
            holder.tv_icon_f_like.setTextColor(Utils.getColor(parent, R.color.feeds_heart_icon_fill));
        }

        holder.tv_icon_f_like.setId(position);
        holder.tv_icon_f_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                */
/*Hide DashBoard postFrame*//*

                parent.hidePostFrameView();
                int position = v.getId();
                feedsListModel = feedsList.get(position);
                if (feedsListModel.isLiked()) {
                    Animation animZoomOut1 = AnimationUtils.loadAnimation(parent, R.anim.zoom_out);
                    holder.tv_icon_f_like.startAnimation(animZoomOut1);
                    holder.tv_icon_f_like.setText(Utils.getStrings(parent, R.string.icon_user_feeds_dislike));
                    holder.tv_icon_f_like.setTextColor(Utils.getColor(parent, R.color.feeds_icon_color));
                    feedsListModel.setFeed_like_count("" + (Integer.parseInt(feedsListModel.getFeed_like_count()) - 1));
                    feedsListModel.setLiked(false);
                    feedsListModel.setFeed_liked("");
                    holder.tv_f_like_count.setText(feedsListModel.getFeed_like_count());
                    feedsList.set(position, feedsListModel);

                     */
/*HEARTS OR HEART*//*

                    holder.tv_f_like_label.setText(FontFamily.getHeartLabel(parent, feedsListModel.getFeed_like_count()));
                } else {
                    Animation animation1 = AnimationUtils.loadAnimation(parent, R.anim.zoom);
                    holder.tv_icon_f_like.startAnimation(animation1);
                    holder.tv_icon_f_like.setText(Utils.getStrings(parent, R.string.icon_user_feeds_like));
                    holder.tv_icon_f_like.setTextColor(Utils.getColor(parent, R.color.feeds_heart_icon_fill));
                    feedsListModel.setFeed_like_count("" + (Integer.parseInt(feedsListModel.getFeed_like_count()) + 1));
                    feedsListModel.setLiked(true);
                    feedsListModel.setFeed_liked("liked");
                    holder.tv_f_like_count.setText(feedsListModel.getFeed_like_count());
                    feedsList.set(position, feedsListModel);

                       */
/*HEARTS OR HEART*//*

                    holder.tv_f_like_label.setText(FontFamily.getHeartLabel(parent, feedsListModel.getFeed_like_count()));
                }
                mlikePostion = position;
                addFameLike(feedsListModel.getFeed_id(), feedsListModel.isLiked() ? AppConstants.TARGET_LIKE :
                        AppConstants.TARGET_UNLIKE);

            }
        });


        */
/*SHARE BUTTON*//*

        holder.tv_icon_f_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                */
/*Hide DashBoard postFrame*//*

                parent.hidePostFrameView();
                shareContent = new ShareContent(parent);
                CustomDialog.showShareDialog(parent, shareContent, feedsListModel);
            }
        });


        */
/*MORE BUTTON*//*

        holder.tv_icon_f_more.setTag(position);

      */
/*  if (userFeedsView != null)
            holder.tv_icon_f_more.setOnClickListener(userFeedsView);*//*



        holder.tv_icon_f_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  */
/*HIDE DASHBOARD POST FRAME*//*

                parent.hidePostFrameView();
                String userId = Preference.getPrefStringData(parent, Preference.KEY_PREF_USER_ID);
                FeedsListModel model = feedsList.get((int) v.getTag());
                String feed_user_id = FontFamily.setServerText(model.getFeed_user_id());

                if (!feed_user_id.equalsIgnoreCase(userId))
                    showMoreReportDialog(parent, model, v);
                else
                    showMoreEditDialog(parent, model, v);
            }
        });

        holder.ll_feeds_main_header.setTag(position);
        holder.tv_f_user_f_l.setTag(position);
        holder.tv_f_time_stamp.setTag(position);

        holder.ll_tags.setTag(position);
        holder.fl_main_image_or_video.setTag(position);
        holder.tv_f_like_label.setTag(position);
        holder.tv_f_comment_count.setTag(position);
        holder.ll_comments_header.setTag(position);
        holder.tv_f_user_tag_count.setTag(position);
        holder.tv_f_post.setTag(position);
    }

    private void sendFollowingUserStatus(String followStatus, String friedId) {

        if (followUserTask != null)
            followUserTask.cancel(true);

        CustomDialog.showProgressDialog(parent, false);

        String access_token = Preference.getPrefStringData(parent, Preference.KEY_PREF_ACCESS_TOKEN);
        String userId = Preference.getPrefStringData(parent, Preference.KEY_PREF_USER_ID);
        String url = String.format(Utils.getServer(parent, R.string.REST_FOLLOW_USER), friedId, userId);

        followUserTask = new JSONTask(this, parent);
        if (followStatus != null && (followStatus.equals("") || followStatus.equalsIgnoreCase("unfollow"))) {
            followUserTask.setMethod(JSONTask.METHOD.POST);
        } else
            followUserTask.setMethod(JSONTask.METHOD.DELETE);
        followUserTask.setServerUrl(url);
        followUserTask.setHeader(ApiConfiguration.AUTHORIZATION, access_token);
        followUserTask.setCode(ApiConfiguration.REST_UNFOLLOW_USER);
        followUserTask.setErrorMessage(ApiConfiguration.ERROR_RESPONSE_CODE);
        followUserTask.setConnectTimeout(ApiConfiguration.TIMEOUT);
        followUserTask.execute();
    }


    @Override
    public void onClick(View v) {

        */
/*Hide DashBoard postFrame*//*

        parent.hidePostFrameView();

        switch (v.getId()) {
            case R.id.iv_f_user_pic:
            case R.id.ll_user_name_and_time:
            case R.id.iv_f_user_tag_1:
            case R.id.iv_f_user_tag_2:
                navigateOtherUserFeedScreen(v.getTag().toString());
                break;
            case R.id.ll_comments_header:
                navigateToCommentFragment((int) v.getTag());
                break;
            case R.id.tv_f_like_label:
                navigateToLikesFragment((int) v.getTag());
                break;
            case R.id.tv_f_comment_count:
                navigateToLikesFragment((int) v.getTag());
                break;
            case R.id.fl_main_image_or_video:
                navigateToFeedDetailFragment((int) v.getTag());
                break;
            case R.id.tv_f_post:
                navigateToFeedDetailFragment((int) v.getTag());
                break;
            case R.id.tv_f_user_tag_count:
                getTagsListOfFriends((int) v.getTag());
                break;
        }

    }

    private void getTagsListOfFriends(int pos) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.INTENT_KEY_FRAME_ID, feedsList.get(pos).getFeed_id());
        bundle.putString(AppConstants.INTENT_KEY_FRAME_TYPE, FEEDS_TYPE);
        bundle.putString(AppConstants.INTENT_KEY_SCREEN_TYPE, AppConstants.TYPE_TAGS);
        bundle.putString(AppConstants.VIEW_TYPE, screenFrom);
        bundle.putBoolean(AppConstants.FROM, false);
        Utils.navigateFragment(new FeedsLikeAndCommentsFragment(), FeedsLikeAndCommentsFragment.TAG, bundle, parent);
    }


    private void navigateToFeedDetailFragment(int pos) {
        try {
            mImageModel = feedsList.get(pos).getFeed_images();
            feedsListModel = feedsList.get(pos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String feedUserId = FontFamily.setServerText(feedsListModel.getFeed_user_id());

        String image = "";
        if (image != null)
            image = mImageModel.getOriginal();

        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.INTENT_KEY_FRAME_ID, feedsList.get(pos).getFeed_id());
        bundle.putString(AppConstants.VIEW_TYPE, screenFrom);
        bundle.putString(AppConstants.INTENT_KEY_FRAME_TYPE, FEEDS_TYPE);
        bundle.putString(AppConstants.INTENT_KEY_FRAME_IMAGE, image);
        bundle.putString(AppConstants.FEED_USER_ID, feedUserId);
        bundle.putString(AppConstants.TYPE_OF_COMMUNITY, typeOfCommunity);
        bundle.putParcelable(AppConstants.FEED_LIST_MODEL, feedsListModel);

        Utils.navigateFragment(new FeedsDetailFragment(), FeedsDetailFragment.TAG, bundle, parent);
    }

    private void navigateToCommentFragment(int pos) {
        Bundle bundle = new Bundle();
        ImageModel model = feedsList.get(pos).getFeed_images();
        String image = "";

        String mediaType = model.getType();
        if (image != null && mediaType.equalsIgnoreCase("video")) {
            image = model.getThumbnail();
        } else if (image != null && mediaType.equalsIgnoreCase("image")) {
            image = model.getOriginal();
        }

        bundle.putString(AppConstants.INTENT_KEY_FRAME_ID, feedsList.get(pos).getFeed_id());
        bundle.putString(AppConstants.INTENT_KEY_FRAME_TYPE, FEEDS_TYPE);
        bundle.putString(AppConstants.INTENT_KEY_FRAME_IMAGE, image);
        bundle.putString(AppConstants.INTENT_KEY_SCREEN_TYPE, AppConstants.TYPE_COMMENTS);
        bundle.putString(AppConstants.VIEW_TYPE, screenFrom);
        bundle.putBoolean(AppConstants.FROM, false);
        Utils.navigateFragment(new FeedsLikeAndCommentsFragment(), FeedsLikeAndCommentsFragment.TAG, bundle, parent);
    }

    private void navigateToLikesFragment(int pos) {
        Bundle bundle = new Bundle();
        ImageModel model = feedsList.get(pos).getFeed_images();
        String image = "";

        String mediaType = model.getType();
        if (image != null && mediaType.equalsIgnoreCase("video")) {
            image = model.getThumbnail();
        } else if (image != null && mediaType.equalsIgnoreCase("image")) {
            image = model.getOriginal();
        }

        bundle.putString(AppConstants.INTENT_KEY_FRAME_ID, feedsList.get(pos).getFeed_id());
        bundle.putString(AppConstants.INTENT_KEY_FRAME_TYPE, FEEDS_TYPE);
        bundle.putString(AppConstants.INTENT_KEY_FRAME_IMAGE, image);
        bundle.putString(AppConstants.INTENT_KEY_SCREEN_TYPE, AppConstants.TYPE_LIKERS);
        bundle.putString(AppConstants.VIEW_TYPE, screenFrom);
        bundle.putBoolean(AppConstants.FROM, false);
        Utils.navigateFragment(new FeedsLikeAndCommentsFragment(), FeedsLikeAndCommentsFragment.TAG, bundle, parent);
    }

    private void addFameLike(String frameId, String lakeStatus) {

        String access_token = Preference.getPrefStringData(parent, Preference.KEY_PREF_ACCESS_TOKEN);
        String userId = Preference.getPrefStringData(parent, Preference.KEY_PREF_USER_ID);

        String url = String.format(Utils.getServer(parent, R.string.REST_Add_Frame__Likes), frameId);
        HashMap<?, ?> params = InputParams.getFrameLikes(userId);
        if (lakeStatus.equals(AppConstants.TARGET_LIKE)) {

            if (postLikeTask != null)
                postLikeTask.cancel(true);

            postLikeTask = new JSONTask(this, parent);
            postLikeTask.setMethod(JSONTask.METHOD.POST);
            postLikeTask.setHeader(ApiConfiguration.AUTHORIZATION, access_token);
            postLikeTask.setCode(ApiConfiguration.REST_ADD_FRAME_LIKES);
            postLikeTask.setParams(params);
            postLikeTask.setServerUrl(url);
            postLikeTask.setErrorMessage(ApiConfiguration.ERROR_RESPONSE_CODE);
            postLikeTask.setConnectTimeout(ApiConfiguration.TIMEOUT);
            postLikeTask.execute();
        } else {

            if (deleteLikeTask != null)
                deleteLikeTask.cancel(true);

            url = url + "?friend_id=" + userId;
            deleteLikeTask = new JSONTask(this, parent);
            deleteLikeTask.setMethod(JSONTask.METHOD.DELETE);
            deleteLikeTask.setHeader(ApiConfiguration.AUTHORIZATION, access_token);
            deleteLikeTask.setServerUrl(url);
            deleteLikeTask.setCode(ApiConfiguration.REST_ADD_FRAME_LIKES);
            deleteLikeTask.setErrorMessage(ApiConfiguration.ERROR_RESPONSE_CODE);
            deleteLikeTask.setConnectTimeout(ApiConfiguration.TIMEOUT);
            deleteLikeTask.execute();

        }


    }


    @Override
    public void successJsonResult(int code, Object result) {

        */
/*LIKE AND DIS_LIKE *//*

        if (code == ApiConfiguration.REST_ADD_FRAME_LIKES) {
            try {
                JSONObject jObject = (JSONObject) result;
                boolean status = jObject.getBoolean("status");
                JSONObject likesObj = jObject.getJSONObject("data");
                int likes = likesObj.getInt("likes");
                int commentsCount = likesObj.getInt("comments");
                String likeStatus = likesObj.getString("is_liked");
                feedsList.get(mlikePostion).setFeed_like_count("" + likes);
                String frameId = feedsList.get(mlikePostion).getFeed_id();
                updateFrameLikeCommentCount(frameId, "" + likes, "" + commentsCount, true, likeStatus);
                if (status) {
                    AppLog.v("FEEDS LIST ADAPTER", "LIKE STATUS:" + status);
                } else
                    AppLog.v("FEEDS LIST ADAPTER", "LIKE STATUS:" + status);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (code == ApiConfiguration.REST_UPDATE_DELETE_FRAME) {
            try {
                JSONObject jObject = (JSONObject) result;
                boolean status = jObject.getBoolean("status");
                String message = jObject.getString("message");
                if (status) {
                    JSONObject obj = jObject.getJSONObject("data");
                    String frame_id = obj.getString("frame_id");
                    AppLog.v("FEEDS LIST ADAPTER", "DELETED FRAME ID:" + frame_id);
                    Validations.setSuccessSnackBar(parent, view, "" + message);
                    feedsList.remove(deletePosition);
                    updateAdapterData(feedsList);
                } else
                    Validations.setSnackBar(parent, view, "" + message);

            } catch (Exception e) {
                e.printStackTrace();
                NetworkUtils.showAlertDialog(parent, deleteFrameTask.getResultMessage());
            }

        } else if (code == ApiConfiguration.REST_REPORT_FRAME) {
            try {
                JSONObject jsonObj = (JSONObject) result;
                String responseStatus = jsonObj.getString("status");
                String responseMessage = jsonObj.getString("message");

                if (responseStatus.equalsIgnoreCase("true")) {
                    Validations.setSuccessSnackBar(parent, view, responseMessage);
                    AppLog.v("FEEDS LIST ADAPTER", "REPORT A FRAME STATUS:" + responseStatus);
                } else {
                    Validations.setSuccessSnackBar(parent, view, responseMessage);
                    AppLog.v("FEEDS LIST ADAPTER", "REPORT A FRAME STATUS:" + responseStatus);
                }

            } catch (Exception e) {
                e.printStackTrace();
                NetworkUtils.showAlertDialog(parent, deleteFrameTask.getResultMessage());
            }
        }

        */
/*BLOCK USER*//*

        else if (code == ApiConfiguration.REST_BLOCK_USER) {
            try {
                JSONObject jsonObj = (JSONObject) result;
                String responseStatus = jsonObj.getString("status");
                String responseMessage = jsonObj.getString("message");

                if (responseStatus.equalsIgnoreCase("true")) {
                    Validations.setSnackBar(parent, tv_f_user_follow, "" + responseMessage);

                    JSONObject dataObj = jsonObj.getJSONObject("data");
                    String blockedUserId = dataObj.getString("user_id");
                    JSONObject countsObj = dataObj.getJSONObject("counts");
                    String following_members = countsObj.getString("following");
                    String favorites_members = countsObj.getString("favorites");

                    parent.blockedUserId = blockedUserId;
                    parent.followersCount = following_members;
                    parent.favoritesCount = favorites_members;
                    if (parent.blockedUsersList != null) {
                        parent.blockedUsersList.add(blockedUserId);
                    }
                    */
/*BLOCKED FRIEND ID *//*

                    if (typeOfCommunity.equalsIgnoreCase(AppConstants.USER_TYPE_CURRENT))
                        UserFeedsView.getInstanceNew().getUpdateCommunityData(blockedUserId);
                    else
                        CommunityFeedsView.getInstanceNew().getUpdateCommunityData(blockedUserId);
                } else
                    Validations.setSnackBar(parent, tv_f_user_follow, "" + responseMessage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        */
/*FALLOW AND INVITE USER*//*

        else if (code == ApiConfiguration.REST_UNFOLLOW_USER)
            try {
                JSONObject jsonObj = (JSONObject) result;
                String responseStatus = jsonObj.getString("status");
                String responseMessage = jsonObj.getString("message");

                if (responseStatus.equalsIgnoreCase("true")) {

                    JSONObject dataObj = jsonObj.getJSONObject("data");
                    String relation = dataObj.getString("relation");

                    if (relation.equalsIgnoreCase("follow") || relation.equalsIgnoreCase("friend")) {
                        tv_f_user_follow.setVisibility(View.GONE);
                    } else if (relation.equalsIgnoreCase("invited")) {
                        tv_f_user_follow.setVisibility(View.VISIBLE);
                        tv_f_user_follow.setText(Utils.getStrings(parent, R.string.txt_invite_plus));
                        tv_f_user_follow.setTextColor(Utils.getColor(parent, R.color.colorPrimary));
                        tv_f_user_follow.setBackground(Utils.getDrawable(parent, R.drawable.bg_btn_fallow));
                        tv_f_user_follow.setClickable(false);
                    }

                    if (feedsList != null) {
                        for (int i = 0; i < feedsList.size(); i++) {
                            FeedsListModel mFeedsListModel = feedsList.get(i);
                            String userId = mFeedsListModel.getFeed_user_id();
                            if (UpdatedUserId.equals(userId)) {
                                int pos = mFeedsListModel.getSelectedPos();
                                feedsList.get(i).setFeed_user_fallow(relation);
                            }
                        }
                        notifyDataSetChanged();
                    }
                } else {
                    Validations.setThreeLinesSnackBar(parent, tv_f_user_follow, responseMessage);
                }
            } catch (Exception e) {
                e.printStackTrace();
                NetworkUtils.showAlertDialog(parent, followUserTask.getResultMessage());
            }
        CustomDialog.hideProgressBar(parent);
    }

    @Override
    public void failedJsonResult(int code) {

        */
/*LIKE AND DIS_LIKE *//*

        if (code == ApiConfiguration.REST_ADD_FRAME_LIKES) {
            AppLog.v("FEEDS LIST ADAPTER", "LIKE STATUS: false");
        } else if (code == ApiConfiguration.REST_REPORT_FRAME) {
            AppLog.v("FEEDS LIST ADAPTER", "REPORT A FRAME STATUS: false");
        }


        CustomDialog.hideProgressBar(parent);
    }


    class DataViewHolder extends RecyclerView.ViewHolder {


        private LinearLayout ll_comments_header;
        private FrameLayout fl_main_image_or_video;

        private LinearLayout ll_feeds_main_header;
        private ImageView iv_f_user_pic;
        private ImageView iv_f_user_tag_1;
        private ImageView iv_f_user_tag_2;
        private ImageView iv_f_post;
        private ImageView iv_icon_f_video;

        private TextView tv_f_user_f_l;
        private TextView tv_f_time_stamp;
        private TextView tv_f_user_follow;
        private TextView tv_f_user_tag_count;
        private TextView tv_f_post;
        private TextView tv_icon_f_share;
        private TextView tv_icon_f_more;
        private TextView tv_icon_f_comment;
        private TextView tv_f_comment_count;
        private TextView tv_f_comment_label;
        private TextView tv_icon_f_like;
        private TextView tv_f_like_count;
        private TextView tv_f_like_label;

        private ProgressBar pb_f_post;
        private TextureVideoView texture_video_view_post;
        private ImageView iv_thumb_video;

        private LinearLayout ll_tags;
        private LinearLayout ll_user_name_and_time;


        private DataViewHolder(View itemView) {
            super(itemView);

            ll_comments_header = (LinearLayout) itemView.findViewById(R.id.ll_comments_header);
            ll_comments_header.setOnClickListener(FeedsListAdapter.this);

            fl_main_image_or_video = (FrameLayout) itemView.findViewById(R.id.fl_main_image_or_video);
            fl_main_image_or_video.setOnClickListener(FeedsListAdapter.this);

            ll_feeds_main_header = (LinearLayout) itemView.findViewById(R.id.ll_feeds_main_header);
            ll_feeds_main_header.setOnClickListener(FeedsListAdapter.this);


            ll_tags = (LinearLayout) itemView.findViewById(R.id.ll_tags);
            ll_tags.setOnClickListener(FeedsListAdapter.this);

            ll_user_name_and_time = (LinearLayout) itemView.findViewById(R.id.ll_user_name_and_time);
            ll_user_name_and_time.setOnClickListener(FeedsListAdapter.this);


            */
/*USER PROFILE PIC*//*

            iv_f_user_pic = (ImageView) itemView.findViewById(R.id.iv_f_user_pic);
            iv_f_user_pic.setOnClickListener(FeedsListAdapter.this);

            */
/*USER NAME*//*

            tv_f_user_f_l = (TextView) itemView.findViewById(R.id.tv_f_user_f_l);
            tv_f_user_f_l.setTypeface(FontFamily.setDefaultFont(parent), Typeface.BOLD);


            */
/*TIME STAMP*//*

            tv_f_time_stamp = (TextView) itemView.findViewById(R.id.tv_f_time_stamp);
            tv_f_time_stamp.setTypeface(FontFamily.setDefaultFont(parent));

            */
/*FOLLOW*//*

            tv_f_user_follow = (TextView) itemView.findViewById(R.id.tv_f_user_follow);
            tv_f_user_follow.setTypeface(FontFamily.setDefaultFont(parent));

            */
/*TAGS*//*

            tv_f_user_tag_count = (TextView) itemView.findViewById(R.id.tv_f_user_tag_count);
            tv_f_user_tag_count.setOnClickListener(FeedsListAdapter.this);
            tv_f_user_tag_count.setTypeface(FontFamily.setDefaultFont(parent));
            iv_f_user_tag_1 = (ImageView) itemView.findViewById(R.id.iv_f_user_tag_1);
            iv_f_user_tag_1.setOnClickListener(FeedsListAdapter.this);
            iv_f_user_tag_2 = (ImageView) itemView.findViewById(R.id.iv_f_user_tag_2);
            iv_f_user_tag_2.setOnClickListener(FeedsListAdapter.this);



            */
/*FRAME POST TEXT*//*

            tv_f_post = (TextView) itemView.findViewById(R.id.tv_f_post);
            tv_f_post.setTypeface(FontFamily.setDefaultFont(parent));
            tv_f_post.setOnClickListener(FeedsListAdapter.this);
            tv_f_post.setLinkTextColor(TextUtils.getHashTagColor(parent));
            tv_f_post.setMovementMethod(LinkMovementMethod.getInstance());

            iv_f_post = (ImageView) itemView.findViewById(R.id.iv_f_post);

            iv_icon_f_video = (ImageView) itemView.findViewById(R.id.iv_icon_f_video);
            pb_f_post = (ProgressBar) itemView.findViewById(R.id.pb_f_post);

            */
/*SHARE*//*

            tv_icon_f_share = (TextView) itemView.findViewById(R.id.tv_icon_f_share);
            tv_icon_f_share.setTypeface(FontFamily.setIconFont(parent));
            tv_icon_f_share.setText("");
            tv_icon_f_share.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_share, 0);


            */
/*MORE*//*

            tv_icon_f_more = (TextView) itemView.findViewById(R.id.tv_icon_f_more);
            tv_icon_f_more.setTypeface(FontFamily.setIconFont(parent));


            */
/*COMMENT*//*

            tv_icon_f_comment = (TextView) itemView.findViewById(R.id.tv_icon_f_comment);
            tv_icon_f_comment.setTypeface(FontFamily.setIconFont(parent));
            tv_f_comment_label = (TextView) itemView.findViewById(R.id.tv_f_comment_label);
            tv_f_comment_label.setTypeface(FontFamily.setDefaultFont(parent));
            tv_f_comment_count = (TextView) itemView.findViewById(R.id.tv_f_comment_count);
            tv_f_comment_count.setTypeface(FontFamily.setDefaultFont(parent), Typeface.BOLD);
            tv_f_comment_count.setOnClickListener(FeedsListAdapter.this);


            */
/*LIKE*//*

            tv_icon_f_like = (TextView) itemView.findViewById(R.id.tv_icon_f_like);
            tv_icon_f_like.setTypeface(FontFamily.setIconFont(parent));
            tv_f_like_label = (TextView) itemView.findViewById(R.id.tv_f_like_label);
            tv_f_like_label.setTypeface(FontFamily.setDefaultFont(parent));
            tv_f_like_label.setOnClickListener(FeedsListAdapter.this);
            tv_f_like_count = (TextView) itemView.findViewById(R.id.tv_f_like_count);
            tv_f_like_count.setTypeface(FontFamily.setDefaultFont(parent), Typeface.BOLD);

            */
/*AUTO PLAY TEXTURE VIDEO VIEW *//*


            texture_video_view_post = (TextureVideoView) itemView.findViewById(R.id.texture_video_view_post);

            */
/*THUMBNAIL*//*

            iv_thumb_video = (ImageView) itemView.findViewById(R.id.iv_thumb_video);


        }

    }

    public void updateAdapterData(ArrayList<FeedsListModel> mList) {
        this.feedsList = mList;
        notifyDataSetChanged();
    }

    private void setFrameText(TextView tv, FeedsListModel model) {

        */
/*SET THE FRAME POST TEXT*//*

        if (!Utils.isValueNullOrEmpty(model.getFeed_tag_location_name())) {

            SpannableString frameText = TextUtils.getFramePostText(parent, model);
            SpannableString location = TextUtils.getCheckInLocationSpan(parent,
                    model.getFeed_tag_location_name());

            tv.setText(android.text.TextUtils.concat(frameText, " ", location));
        } else {
            SpannableString frameText = TextUtils.getFramePostText(parent, model);
            tv.setText(frameText);
        }


    }


    private void navigateOtherUserFeedScreen(String friendId) {

        String currentUserId = Preference.getPrefStringData(parent, Preference.KEY_PREF_USER_ID);

        if (!friendId.equals(currentUserId)) {
            ColorUtils.setBottomSelectedColor(parent, parent.ll_dash_bottom, ColorUtils.REMOVE_POSITION);
            Bundle bundle = new Bundle();
            bundle.putString(AppConstants.OTHER_USER_ID, friendId);
            Utils.navigateFragment(new OtherUserFeedsFragment(), OtherUserFeedsFragment.TAG, bundle, parent);
        } else if (FEEDS_TYPE.equals(AppConstants.FEEDS_TYPE_COMMUNITY)) {
            ColorUtils.setBottomSelectedColor(parent, parent.ll_dash_bottom, ColorUtils.HOME_POSITION);
            parent.navigateToHomeFeedsFragment();
        } else {
            ColorUtils.setBottomSelectedColor(parent, parent.ll_dash_bottom, ColorUtils.HOME_POSITION);
            parent.navigateToHomeFeedsFragment();
        }

    }

    private void showMoreEditDialog(final DashBoardActivity parent, final FeedsListModel feedsListModel, View view) {

        final Dialog dialog = new Dialog(parent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_more);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Button btn_edit = (Button) dialog.findViewById(R.id.btn_edit);
        btn_edit.setTypeface(FontFamily.setDefaultFont(parent), Typeface.BOLD);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent, PostFrameActivity.class);
                intent.putExtra(AppConstants.INTENT_KEY_FRAME_POST_NAVIGATE_TYPE,
                        AppConstants.INTENT_KEY_POST_FRAME_TEXT);
                intent.putExtra(AppConstants.INTENT_KEY_WHERE_FROM, AppConstants.INTENT_KEY_TO_POST_FRAME);
                intent.putExtra(AppConstants.INTENT_KEY_POST_FRAME_TYPE, AppConstants.INTENT_KEY_POST_EDIT_FRAME);
                intent.putExtra(AppConstants.INTENT_KEY_POST_USER_INFO, parent.userModel);
                intent.putExtra(AppConstants.INTENT_KEY_POST_FRAME_INFO, feedsListModel);
                intent.putExtra(AppConstants.INTENT_KEY_POST_IMAGE_INFO, feedsListModel.getFeed_images());
                parent.startActivityForResult(intent, AppConstants.INTENT_KEY_EDIT_FRAME);
                dialog.dismiss();
            }
        });

        Button btn_delete = (Button) dialog.findViewById(R.id.btn_delete);
        btn_delete.setTypeface(FontFamily.setDefaultFont(parent), Typeface.BOLD);
        btn_delete.setTextColor(Utils.getColor(parent, R.color.feeds_heart_icon_fill));
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteUserDialog(parent, feedsListModel);
                dialog.dismiss();
            }
        });


        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setTypeface(FontFamily.setDefaultFont(parent), Typeface.BOLD);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void showDeleteUserDialog(final DashBoardActivity parent, final FeedsListModel model) {

        final Dialog mDialog = new Dialog(parent);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_network_check);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(true);

        TextView tv_alert_dialog_title = (TextView) mDialog.findViewById(R.id.tv_alert_dialog_title);
        tv_alert_dialog_title.setTypeface(FontFamily.setDefaultFont(parent), Typeface.BOLD);
        tv_alert_dialog_title.setText(Utils.getStrings(parent, R.string.app_name));

        TextView tv_dialog_content = (TextView) mDialog.findViewById(R.id.tv_dialog_content);
        tv_dialog_content.setTypeface(FontFamily.setDefaultFont(parent));
        tv_dialog_content.setText(Utils.getStrings(parent, R.string.are_u_sure));


        */
/*YES*//*

        Button btn_dialog_ok = (Button) mDialog.findViewById(R.id.btn_dialog_ok);
        btn_dialog_ok.setTypeface(FontFamily.setDefaultFont(parent));
        btn_dialog_ok.setTextColor(Utils.getColor(parent, R.color.black_yash));
        btn_dialog_ok.setText(Utils.getStrings(parent, R.string.txt_cancel));
        btn_dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        */
/*CANCEL*//*

        Button btn_dialog_cancel = (Button) mDialog.findViewById(R.id.btn_dialog_cancel);
        btn_dialog_cancel.setTypeface(FontFamily.setDefaultFont(parent));
        btn_dialog_cancel.setTextColor(Utils.getColor(parent, R.color.colorAccent));
        btn_dialog_cancel.setText(Utils.getStrings(parent, R.string.txt_yes));
        btn_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                deleteFrameTask(model);
            }
        });

        mDialog.show();
    }
    */
/*DELETE FRAME DIALOG*//*


    private void deleteFrameTask(FeedsListModel feedsListModel) {

        if (deleteFrameTask != null)
            deleteFrameTask.cancel(true);

        CustomDialog.showProgressDialog(parent, false);


        String access_token = Preference.getPrefStringData(parent, Preference.KEY_PREF_ACCESS_TOKEN);
        String userId = Preference.getPrefStringData(parent, Preference.KEY_PREF_USER_ID);

        String frameId = FontFamily.setServerText(feedsListModel.getFeed_id());

        */
/* SELECTED FRAME POSITION*//*


        deletePosition = feedsListModel.getSelectedPos();

        String url = String.format(Utils.getServer(parent, R.string.REST_Share_Update_Delete), userId, frameId);

        deleteFrameTask = new JSONTask(this, parent);
        deleteFrameTask.setMethod(JSONTask.METHOD.DELETE);
        deleteFrameTask.setHeader(ApiConfiguration.AUTHORIZATION, access_token);
        deleteFrameTask.setServerUrl(url);
        deleteFrameTask.setCode(ApiConfiguration.REST_UPDATE_DELETE_FRAME);
        deleteFrameTask.setErrorMessage(ApiConfiguration.ERROR_RESPONSE_CODE);
        deleteFrameTask.setConnectTimeout(ApiConfiguration.TIMEOUT);
        deleteFrameTask.execute();


    }

    private void showMoreReportDialog(final BaseActivity parent, final FeedsListModel feedsListModel, View view) {

        dialogMore = new Dialog(parent);
        dialogMore.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogMore.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogMore.setContentView(R.layout.dialog_more);
        dialogMore.getWindow().setGravity(Gravity.BOTTOM);
        dialogMore.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dialogMore.setCancelable(true);
        dialogMore.setCanceledOnTouchOutside(false);
        dialogMore.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        Button btn_block = (Button) dialogMore.findViewById(R.id.btn_edit);
        btn_block.setTypeface(FontFamily.setDefaultFont(parent), Typeface.BOLD);
        btn_block.setText(Utils.getStrings(parent, R.string.txt_block));
        btn_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBlockUserDialog(feedsListModel);
                   */
/* dialogMore.dismiss();*//*


            }
        });

        Button btn_report = (Button) dialogMore.findViewById(R.id.btn_delete);
        btn_report.setTypeface(FontFamily.setDefaultFont(parent), Typeface.BOLD);
        btn_report.setText(Utils.getStrings(parent, R.string.txt_report));
        btn_report.setTextColor(Utils.getColor(parent, R.color.feeds_heart_icon_fill));
        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportFrame(feedsListModel);
                dialogMore.dismiss();
            }
        });


        Button btnCancel = (Button) dialogMore.findViewById(R.id.btn_cancel);
        btnCancel.setTypeface(FontFamily.setDefaultFont(parent), Typeface.BOLD);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogMore.cancel();
            }
        });
        dialogMore.show();
    }

    private void showBlockUserDialog(final FeedsListModel feedsListModel) {

        final Dialog mDialog = new Dialog(parent);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_network_check);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(true);

        TextView tv_alert_dialog_title = (TextView) mDialog.findViewById(R.id.tv_alert_dialog_title);
        tv_alert_dialog_title.setTypeface(FontFamily.setDefaultFont(parent), Typeface.BOLD);
        tv_alert_dialog_title.setText(Utils.getStrings(parent, R.string.txt_block));


        TextView tv_dialog_content = (TextView) mDialog.findViewById(R.id.tv_dialog_content);
        tv_dialog_content.setTypeface(FontFamily.setDefaultFont(parent));
        tv_dialog_content.setText(Utils.getStrings(parent, R.string.txt_are_you_sure_u_want));


        */
/*YES*//*

        Button btn_dialog_ok = (Button) mDialog.findViewById(R.id.btn_dialog_ok);
        btn_dialog_ok.setTypeface(FontFamily.setDefaultFont(parent));
        btn_dialog_ok.setTextColor(Utils.getColor(parent, R.color.black_yash));
        btn_dialog_ok.setText(Utils.getStrings(parent, R.string.txt_cancel));
        btn_dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                dialogMore.cancel();
            }
        });

        */
/*CANCEL*//*

        Button btn_dialog_cancel = (Button) mDialog.findViewById(R.id.btn_dialog_cancel);
        btn_dialog_cancel.setTypeface(FontFamily.setDefaultFont(parent));
        btn_dialog_cancel.setTextColor(Utils.getColor(parent, R.color.colorAccent));
        btn_dialog_cancel.setText(Utils.getStrings(parent, R.string.txt_yes));
        btn_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockUser(feedsListModel);
                mDialog.dismiss();
                dialogMore.dismiss();
            }
        });
        mDialog.show();

    }


    private void blockUser(FeedsListModel feedsListModel) {


        if (blockUserTask != null)
            blockUserTask.cancel(true);

        CustomDialog.showProgressDialog(parent, false);

        String access_token = Preference.getPrefStringData(parent, Preference.KEY_PREF_ACCESS_TOKEN);
        String userId = Preference.getPrefStringData(parent, Preference.KEY_PREF_USER_ID);

        String frameUserId = FontFamily.setServerText(feedsListModel.getFeed_user_id());


        String url = String.format(Utils.getServer(parent, R.string.REST_BLOCK_USER), frameUserId, userId);

        url = url + "?type=%s" + "&community_id=%s";

        if (!Utils.isValueNullOrEmpty(communityId)) {
            url = String.format(url, typeOfCommunity, communityId);
        } else
            url = String.format(url, typeOfCommunity, typeOfCommunity);

        */
/* String url = String.format(Utils.getServer(parent, R.string.REST_BLOCK_USER), frame.getUser_id(), parent.session.getUserid());
        url = url + "?type=%s" + "&community_id=%s";
        url = String.format(url, type, CommunityId);*//*


        blockUserTask = new JSONTask(this, parent);
        blockUserTask.setMethod(JSONTask.METHOD.DELETE);
        blockUserTask.setHeader(ApiConfiguration.AUTHORIZATION, access_token);
        blockUserTask.setServerUrl(url);
        blockUserTask.setCode(ApiConfiguration.REST_BLOCK_USER);
        blockUserTask.setErrorMessage(ApiConfiguration.ERROR_RESPONSE_CODE);
        blockUserTask.setConnectTimeout(ApiConfiguration.TIMEOUT);
        blockUserTask.execute();


    }

    private void reportFrame(FeedsListModel feedsListModel) {
        if (reportFrameTask != null)
            reportFrameTask.cancel(true);

        CustomDialog.showProgressDialog(parent, false);


        String access_token = Preference.getPrefStringData(parent, Preference.KEY_PREF_ACCESS_TOKEN);
        String userId = Preference.getPrefStringData(parent, Preference.KEY_PREF_USER_ID);

        String frameId = FontFamily.setServerText(feedsListModel.getFeed_id());

        HashMap<String, String> param = InputParams.getJSONReportFrameParam(userId);


        String url = String.format(Utils.getServer(parent, R.string.REST_Report_Frame), frameId);

        reportFrameTask = new JSONTask(this, parent);
        reportFrameTask.setMethod(JSONTask.METHOD.POST);
        reportFrameTask.setParams(param);
        reportFrameTask.setHeader(ApiConfiguration.AUTHORIZATION, access_token);
        reportFrameTask.setServerUrl(url);
        reportFrameTask.setCode(ApiConfiguration.REST_REPORT_FRAME);
        reportFrameTask.setErrorMessage(ApiConfiguration.ERROR_RESPONSE_CODE);
        reportFrameTask.setConnectTimeout(ApiConfiguration.TIMEOUT);
        reportFrameTask.execute();
    }

    */
/* public void removeBlockedUsersFrame(String blockedUserId) {
         if (feedsList != null) {
             List<FeedsListModel> toRemove = new ArrayList<>();
             for (FeedsListModel feedsListModel : feedsList) {
                 String userId = feedsListModel.getFeed_user_id();

                 if (blockedUserId.equals(userId)) {
                     toRemove.add(feedsListModel);

                 }
             }
             if (toRemove.size() > 0) {
                 feedsList.removeAll(toRemove);
             }

             *//*
*/
/*CommunityFeedsView.getInstanceNew().getUpdateCommunityData();*//*
*/
/*


            *//*
*/
/*if (feedsList.size() == 1) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
               *//*
*/
/**//*
*/
/* int marginBottom = calculatePadding();
                params.setMargins(0, 0, 0, marginBottom);*//*
*/
/**//*
*/
/*
                parent.mPullToRefreshView.setLayoutParams(params);
            }*//*
*/
/*
            if (feedsList.size() <= 5) {
                CommunityFeedsView.getInstanceNew().getUpdateCommunityData();
            }
            CommunityFeedsView.getInstanceNew().getUpdateCommunityData();
           *//*
*/
/* updateAdapterData(feedsList);*//*
*/
/*
        }
    }*//*

    private int isFrameIdExist(String frameId) {
        for (int i = 0; i < parent.frameLikeCommentslist.size(); i++) {
            FrameLikeComment flc = parent.frameLikeCommentslist.get(i);
            if (flc.getFrameId().equals(frameId))
                return i;

        }

        return -1;
    }

    private void updateFrameLikeCommentCount(String frameId, String likeCount, String commentCount, boolean isLikeStatusUpdate, String likeStatus) {
        int pos = isFrameIdExist(frameId);
        if (pos != -1) {
            FrameLikeComment flc = parent.frameLikeCommentslist.get(pos);
            flc.setCommentCount(commentCount);
            if (isLikeStatusUpdate) {
                flc.setLikeCount(likeCount);
                flc.setLikeStatus(likeStatus);
            }

        } else {
            FrameLikeComment flc = new FrameLikeComment(likeCount, commentCount, frameId, isLikeStatusUpdate, likeStatus);
            parent.frameLikeCommentslist.add(flc);
        }
    }
}
*/
