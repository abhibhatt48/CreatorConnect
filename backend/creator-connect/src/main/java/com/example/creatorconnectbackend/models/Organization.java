package com.example.creatorconnectbackend.models;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * -----------------------------------------------------------------------------
 *                           Organization Model
 * -----------------------------------------------------------------------------
 * 
 * Purpose:
 * The `Organization` class captures the various attributes and details related 
 * to an organization within the 'com.example.creatorconnectbackend' application.
 * 
 * Key Attributes:
 * - orgID: A unique identifier for each organization.
 * - userId: Associated user ID, which could be used for authentication or contact.
 * - orgName: Name of the organization.
 * - profileImage: A reference to the organization's profile image.
 * - companyType: Indicates the type or nature of the company.
 * - size: The number of employees or size of the organization.
 * - websiteLink: URL for the organization's website.
 * - location: The geographical location or headquarters of the organization.
 * - targetInfluencerNiche: Niches or specialties of influencers the organization aims to collaborate with.
 * - bio: A brief introduction or description about the organization.
 * - Social Media Handles: Links or usernames for platforms like Instagram, Facebook, etc.
 * 
 * Usage:
 * The model serves various purposes including:
 * - Registering a new organization.
 * - Displaying organization profiles.
 * - Updating and editing organization details.
 * 
 * Validations:
 * Several fields come with validation constraints to ensure data consistency 
 * and integrity. For instance, certain fields cannot be null or exceed a 
 * specific character length.
 * 
 * Enhancements (Future Consideration):
 * - Add more attributes like founding year, revenue, etc.
 * - Consider methods to evaluate organization reputation or ratings.
 * - Integrate with an analytics module to get insights on organization interactions with influencers.
 * 
 * -----------------------------------------------------------------------------
 */

public class Organization {
	
	private Long orgID;

    private Long userId;

    @NotNull(message = "Organization name cannot be null")
    @Size(min = 1, message = "Organization name cannot be empty")
    private String orgName;

    private String profileImage;

    // Type of company with validation constraints
    @NotNull(message = "Company type cannot be null")
    @Size(min = 1, message = "Company type cannot be empty")
    private String companyType;

    private Long size;

    private String websiteLink;

    private String location;

    private List<String> targetInfluencerNiche;

    // Short bio or description of the organization with validation constraint
    @Size(max = 250, message = "Bio cannot be more than 250 characters")
    private String bio;

    // Social media account links/handles
    private String instagram;
    private String facebook;
    private String twitter;
    private String tiktok;
    private String youtube;
    private String twitch;
    
    // Getters and Setters

    public Long getOrgID() {
		return orgID;
	}

	public void setOrgID(Long orgID) {
		this.orgID = orgID;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getWebsiteLink() {
		return websiteLink;
	}

	public void setWebsiteLink(String websiteLink) {
		this.websiteLink = websiteLink;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

	public List<String> getTargetInfluencerNiche() {
		return targetInfluencerNiche;
	}

	public void setTargetInfluencerNiche(List<String> targetInfluencerNiche) {
		this.targetInfluencerNiche = targetInfluencerNiche;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getTiktok() {
		return tiktok;
	}

	public void setTiktok(String tiktok) {
		this.tiktok = tiktok;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public String getTwitch() {
		return twitch;
	}

	public void setTwitch(String twitch) {
		this.twitch = twitch;
	}
}
