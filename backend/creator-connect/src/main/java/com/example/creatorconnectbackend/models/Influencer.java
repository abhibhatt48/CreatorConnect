package com.example.creatorconnectbackend.models;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

// This class represents an Influencer model with various details and attributes.
public class Influencer {
	
    // Unique identifier for the influencer.
	private Long influencerID;

    // Personal name of the influencer. Cannot be null or empty.
	@NotNull(message = "Name cannot be null")
    @Size(min = 1, message = "Name cannot be empty")
    private String name;

    // URL or path to the profile image of the influencer.
    private String profileImage;

    // Gender of the influencer.
    private Gender gender;

    // Online alias or handle for the influencer. Cannot be null or empty.
	@NotNull(message = "Influencer name cannot be null")
    @Size(min = 1, message = "Influencer name cannot be empty")
    private String influencerName;

    // The type/category of influencer (e.g., beauty, travel, tech, etc.).
    private String influencerType;

    // List of niches or specialties of the influencer.
    private List<String> influencerNiche;

    // Minimum rate the influencer charges for their services.
    private Long minRate;

    // List or string of previous brands the influencer has worked with.
    private String previousBrands;

    // Current location of the influencer.
    private String location;

    // Short bio or description of the influencer. Limited to 250 characters.
	@Size(max = 250, message = "Bio cannot be more than 250 characters")
    private String bio;

    // Birthdate of the influencer. Must be a past date.
	@Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;

    // Social media handles or URLs.
    private String instagram;
    private String tikTok;
    private String tweeter;  // It might be a typo. It should probably be "twitter".
    private String youtube;
    private String facebook;
    private String twitch;

    // List of influencer's best or most recognized posts.
    private List<String> bestPosts;

    // User model associated with this influencer, likely for authentication and other user-specific functions.
    private User user;

    // Getter for influencerID
    public Long getInfluencerID() {
		return influencerID;
	}

    // Setter for influencerID
	public void setInfluencerID(Long influencerID) {
		this.influencerID = influencerID;
	}

    // Getter for name
	public String getName() {
		return name;
	}

    // Setter for name
	public void setName(String name) {
		this.name = name;
	}

    // Getter for profileImage
	public String getProfileImage() {
		return profileImage;
	}

    // Setter for profileImage
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

    // Getter for gender
	public Gender getGender() {
		return gender;
	}

    // Setter for gender
	public void setGender(Gender gender) {
		this.gender = gender;
	}

    // Getter for influencerName
	public String getInfluencerName() {
		return influencerName;
	}

    // Setter for influencerName
	public void setInfluencerName(String influencerName) {
		this.influencerName = influencerName;
	}

    // Getter for influencerType
	public String getInfluencerType() {
		return influencerType;
	}

    // Setter for influencerType
	public void setInfluencerType(String influencerType) {
		this.influencerType = influencerType;
	}

    // Getter for minRate
	public Long getMinRate() {
		return minRate;
	}

    // Setter for minRate
	public void setMinRate(Long minRate) {
		this.minRate = minRate;
	}

    // Getter for previousBrands
	public String getPreviousBrands() {
		return previousBrands;
	}

    // Setter for previousBrands
	public void setPreviousBrands(String previousBrands) {
		this.previousBrands = previousBrands;
	}

    // Getter for location
	public String getLocation() {
		return location;
	}

    // Setter for location
	public void setLocation(String location) {
		this.location = location;
	}

    // Getter for user
	public User getUser() {
		return user;
	}

    // Setter for user
	public void setUser(User user) {
		this.user = user;
	}

    // Getter for influencerNiche list
	public List<String> getInfluencerNiche() {
		return influencerNiche;
	}

    // Setter for influencerNiche list
	public void setInfluencerNiche(List<String> influencerNiche) {
		this.influencerNiche = influencerNiche;
	}

    // Getter for bio
	public String getBio() {
		return bio;
	}

    // Setter for bio
	public void setBio(String bio) {
		this.bio = bio;
	}

    // Getter for birthdate
	public LocalDate getBirthdate() {
		return birthdate;
	}

    // Setter for birthdate
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

    // Getter for instagram
	public String getInstagram() {
		return instagram;
	}

    // Setter for instagram
	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

    // Getter for tikTok
	public String getTikTok() {
		return tikTok;
	}

    // Setter for tikTok
	public void setTikTok(String tikTok) {
		this.tikTok = tikTok;
	}

    // Getter for tweeter
	public String getTweeter() {
		return tweeter;
	}

    // Setter for tweeter
	public void setTweeter(String tweeter) {
		this.tweeter = tweeter;
	}

    // Getter for youtube
	public String getYoutube() {
		return youtube;
	}

    // Setter for youtube
	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

    // Getter for facebook
	public String getFacebook() {
		return facebook;
	}

    // Setter for facebook
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

    // Getter for twitch
	public String getTwitch() {
		return twitch;
	}

    // Setter for twitch
	public void setTwitch(String twitch) {
		this.twitch = twitch;
	}

    // Getter for bestPosts list
	public List<String> getBestPosts() {
		return bestPosts;
	}

    // Setter for bestPosts list
	public void setBestPosts(List<String> bestPosts) {
		this.bestPosts = bestPosts;
	}
}
