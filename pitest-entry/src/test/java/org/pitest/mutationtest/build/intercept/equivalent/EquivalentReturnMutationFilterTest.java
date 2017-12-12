package org.pitest.mutationtest.build.intercept.equivalent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.pitest.mutationtest.engine.gregor.mutators.BooleanFalseReturnValsMutator.BOOLEAN_FALSE_RETURN;
import static org.pitest.mutationtest.engine.gregor.mutators.BooleanTrueReturnValsMutator.BOOLEAN_TRUE_RETURN;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.pitest.mutationtest.build.InterceptorType;
import org.pitest.mutationtest.build.MutationInterceptor;
import org.pitest.mutationtest.build.intercept.javafeatures.FilterTester;
import org.pitest.mutationtest.engine.gregor.mutators.EmptyObjectReturnValsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.PrimitiveReturnsMutator;

public class EquivalentReturnMutationFilterTest {
  
  MutationInterceptor testee = new EquivalentReturnMutationFilter().createInterceptor(null);
  
  FilterTester verifier = new FilterTester("", testee, PrimitiveReturnsMutator.PRIMITIVE_RETURN_VALS_MUTATOR
                                                     , EmptyObjectReturnValsMutator.EMPTY_RETURN_VALUES
                                                     , BOOLEAN_FALSE_RETURN
                                                     , BOOLEAN_TRUE_RETURN);    
  
  @Test
  public void shouldDeclareTypeAsFilter() {
    assertThat(this.testee.type()).isEqualTo(InterceptorType.FILTER);
  }
  
  @Test
  public void doesNotFilterNonEquivalents() {
    verifier.assertFiltersNMutationFromClass(0, ReturnsWidget.class);
  }
  
  @Test
  public void filtersEquivalentPrimitiveIntMutants() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsConstZero.class);
  }
  
  @Test
  public void filtersEquivalentPrimitiveBooleanMutants() {
    verifier.assertFiltersMutationsFromMutator(BOOLEAN_FALSE_RETURN.getGloballyUniqueId()
        , AlreadyReturnsFalse.class);
  }
  
  @Test
  public void filtersEquivalentPrimitiveBooleanTrueMutants() {
    verifier.assertFiltersMutationsFromMutator(BOOLEAN_TRUE_RETURN.getGloballyUniqueId()
        , ReturnsTrue.class);
  }

  @Test
  public void filtersEquivalentPrimitiveLongMutants() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsLong0.class);
  }
  
  @Test
  public void filtersEquivalentPrimitiveFloatMutants() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsFloat0.class);
  }
  
  @Test
  public void filtersEquivalentPrimitiveDoubleMutants() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsDouble0.class);
  }
  
  @Test
  public void filtersEquivalentBoxedBooleanMutants() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsBoxedFalse.class);
  }
  
  @Test
  public void filtersEquivalentBoxedBooleanTrueMutants() {
    verifier.assertFiltersMutationsFromMutator(BOOLEAN_TRUE_RETURN.getGloballyUniqueId()
        , AlreadyReturnsBoxedTrue.class);
  }
  
  @Test
  public void filtersEquivalentBoxedIntegerMutants() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsBoxedZeroInteger.class);
  }
  
  @Test
  public void filtersEquivalentBoxedShortMutants() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsBoxedZeroShort.class);
  }
  
  @Test
  public void filtersEquivalentBoxedLongMutants() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsBoxedZeroLong.class);
  }
  
  @Test
  public void filtersEquivalentBoxedFloatMutants() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsBoxedZeroFloat.class);
  }
  
  @Test
  public void filtersEquivalentBoxedDoubleMutants() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsBoxedZeroDouble.class);
  }
  
  @Test
  public void doesNotFilterOtherSingleParamStaticMethodCalls() {
    verifier.assertFiltersNMutationFromClass(0, CallsAnIntegerReturningStaticWith0.class);
  }
  
  @Test
  public void filtersEquivalentStringMutants() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsEmptyString.class);
  }
  
  @Test
  public void filtersEquivalentStringMutantsWhenEmptyStringHeldAsField() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsEmptyStringFromField.class);
  }
  
  @Test
  public void filtersEquivalentListMutants() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsEmptyList.class);
  }  
  
  @Test
  public void filtersEquivalentSetMutants() {
    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsEmptySet.class);
  }    

// can't include test as must build on java 7  
//  @Test
//  public void filtersEquivalentOptionalMutants() {
//    verifier.assertFiltersNMutationFromClass(1, AlreadyReturnsEmptyOptional.class);
//  }
}

class Widget{}

class ReturnsWidget {
  public Widget a() {
    return new Widget();
  }
}

class ReturnsTrue {
  public boolean a() {
    return true;
  }
}

class AlreadyReturnsConstZero {
  public int a() {
    return 0;
  }
}

class AlreadyReturnsBoxedFalse {
  public Boolean a() {
    return false;
  }
}

class AlreadyReturnsBoxedTrue {
  public Boolean a() {
    return true;
  }
}

class AlreadyReturnsFalse {
  public boolean a() {
    return false;
  }
}

class AlreadyReturnsBoxedZeroInteger {
  public Integer a() {
    return 0;
  }
}

class CallsAnIntegerReturningStaticWith0 {
  
  static Integer foo(int a) {
    return 42;
  }
  
  public Integer a() {
    return foo(0);
  }
}

class AlreadyReturnsBoxedZeroShort {
  public Short a() {
    return 0;
  }
}

class AlreadyReturnsLong0 {
  public long a() {
    return 0;
  }
}

class AlreadyReturnsBoxedZeroLong {
  public Long a() {
    return 0l;
  }
}

class AlreadyReturnsFloat0 {
  public float a() {
    return 0;
  }
}
  
class AlreadyReturnsDouble0 {
  public double a() {
    return 0;
  }
}


class AlreadyReturnsBoxedZeroFloat {
  public Float a() {
    return 0f;
  }
}

class AlreadyReturnsBoxedZeroDouble {
  public Double a() {
    return 0d;
  }
}

class AlreadyReturnsEmptyString {
  public String a() {
    return "";
  }
}

class AlreadyReturnsEmptyStringFromField {
  static final String EMPTY = "";
  public String a() {
    return EMPTY;
  }
}

class AlreadyReturnsEmptyList {
  public List<Integer> a() {
    return Collections.emptyList();
  }
}

class AlreadyReturnsEmptySet {
  public Set<Integer> a() {
    return Collections.emptySet();
  }
}


//class AlreadyReturnsEmptyOptional {
//  public Optional<String> a() {
//    return Optional.empty();
//  }
//}
