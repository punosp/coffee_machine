package com.dunzo.coffeemachine.model;

import com.dunzo.coffeemachine.model.enums.ResponseType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableValidityResponse.class)
@JsonDeserialize(as = ImmutableValidityResponse.class)
public interface ValidityResponse {

    Map<ResponseType, List<String>> ingredients();
}
