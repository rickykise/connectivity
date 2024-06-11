package ai.fassto.connectivity.application.common.validation;

import ai.fassto.connectivity.application.common.validation.group.MinMaxGroup;
import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.application.common.validation.group.PatternCheckGroup;
import ai.fassto.connectivity.application.common.validation.group.PositiveGroup;

import javax.validation.GroupSequence;

@GroupSequence({NotEmptyGroup.class, PositiveGroup.class, MinMaxGroup.class, PatternCheckGroup.class})
public interface ValidationSequence {
}
