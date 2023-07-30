/**
 * -----------------------------------------------------------------------------
 *                         Gender Enumeration
 * -----------------------------------------------------------------------------
 * 
 * Purpose:
 * The 'Gender' enumeration represents the categorical distinctions of 
 * gender within the 'com.example.creatorconnectbackend' application.
 * 
 * Enumerators:
 * - MALE: Represents the male gender category.
 * - FEMALE: Represents the female gender category.
 * - OTHER: Represents any gender category that doesn't conform strictly 
 *          to the male or female distinctions. It could encompass a range 
 *          of non-binary, genderqueer, or other gender identities.
 * 
 * Usage:
 * The 'Gender' enum can be employed in various parts of the application 
 * where gender-specific information or distinction is required. For instance, 
 * it can be used in user profiles, registration forms, or surveys.
 * 
 * Enhancements (Future Consideration):
 * - Expanding the list to provide more specific non-binary or third-gender 
 *   options based on community feedback or international standards.
 * - Incorporate a 'PREFER_NOT_TO_SAY' option for users who prefer not to 
 *   disclose their gender.
 *
 * -----------------------------------------------------------------------------
 */

package com.example.creatorconnectbackend.models;

public enum Gender {
    MALE,

    FEMALE,

    OTHER;
}
