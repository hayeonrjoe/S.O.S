package com.sos.signal.onlinecomplaint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchResult {
    private Integer ocId;
    private String ocTitle;
    private String ocAdvisor;
    private String ocName;
    private String ocDateFormatted;
    private String ocResponseStatus;
}
