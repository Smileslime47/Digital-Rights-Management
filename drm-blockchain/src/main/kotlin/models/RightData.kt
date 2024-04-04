package moe._47saikyo.models

import com.fasterxml.jackson.annotation.JsonProperty

data class RightData(
    @JsonProperty("title")val title: String = "",
    @JsonProperty("owner")val owner: String = "",
    @JsonProperty("registrationNumber")val registrationNumber: String = "",
    @JsonProperty("issueTime")val issueTime: Long = 0,
    @JsonProperty("expireTime")val expireTime: Long = 0,
    @JsonProperty("description")val description: String = "",
    @JsonProperty("available")val available: Boolean = false,
    @JsonProperty("licenses")val licenses: List<String> = emptyList()
)