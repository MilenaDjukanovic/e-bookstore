package com.mixienixie.ebookstore.repo.core.entity;

import lombok.Data;

/**
 * DTO class used for Publishing house view
 *
 * @author mdjukanovic
 */
@Data
public class PublishingHouseDto {

    /** Publishing house id **/
    private Long id;

    /** Company name **/
    private String companyName;

    /** Email **/
    private String email;

    /** TIN **/
    private String TIN;
}
