package com.mixienixie.ebookstore.core.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Base class for Publishing house requests
 *
 * @author mdjukanovic
 */
@Data
public abstract class BasePublishingHouseRequest {

    /** Company name **/
    @NotBlank
    private String companyName;

    /** Email **/
    @NotBlank
    private String email;

    /** TIN **/
    @NotBlank
    private String TIN;
}
