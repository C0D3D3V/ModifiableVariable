/*
 * ModifiableVariable - A Variable Concept for Runtime Modifications
 *
 * Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0 http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.modifiablevariable.integer;

import de.rub.nds.modifiablevariable.VariableModification;
import de.rub.nds.modifiablevariable.bytearray.ByteArrayInsertValueModification;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

@XmlRootElement
@XmlType(propOrder = {"insertValue", "startPosition", "modificationFilter"})
public class IntegerInsertValueModification extends VariableModification<Integer> {

    private static final int MAX_VALUE_MODIFIER = 256;

    private static final int MAX_POSITION_MODIFIER = 32;

    private Integer insertValue;

    private int startPosition;

    public IntegerInsertValueModification() {}

    public IntegerInsertValueModification(Integer insertValue, int startPosition) {
        this.insertValue = insertValue;
        this.startPosition = startPosition;
    }

    @Override
    protected Integer modifyImplementationHook(Integer input) {
        if (input == null) {
            input = 0;
        }
        int originalValueLength = Integer.SIZE - Integer.numberOfLeadingZeros((input));
        int insertValueLength = Integer.SIZE - Integer.numberOfLeadingZeros((insertValue));
        int insertPosition = startPosition;
        if (startPosition > originalValueLength) {
            insertPosition = originalValueLength;
        } else if (startPosition < 0) {
            insertPosition = 0;
        }
        int mask = ((1 << insertPosition) - 1);

        return (((input >> insertPosition) << insertValueLength) + insertValue) << insertPosition + (mask & input);
    }

    public Integer getInsertValue() {
        return insertValue;
    }

    public void setInsertValue(Integer insertValue) {
        this.insertValue = insertValue;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    @Override
    public VariableModification<Integer> getModifiedCopy() {
        Random r = new Random();

        if (r.nextBoolean()) {
            return new IntegerInsertValueModification(
                insertValue + r.nextInt(MAX_VALUE_MODIFIER), startPosition);
        } else {
            int modifier = r.nextInt(MAX_POSITION_MODIFIER);
            if (r.nextBoolean()) {
                modifier *= -1;
            }
            modifier = startPosition + modifier;
            if (modifier <= 0) {
                modifier = 1;
            }
            return new IntegerInsertValueModification(insertValue, modifier);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.insertValue);
        hash = 37 * hash + this.startPosition;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IntegerInsertValueModification other = (IntegerInsertValueModification) obj;
        if (this.startPosition != other.startPosition) {
            return false;
        }
        return Objects.equals(this.insertValue, other.insertValue);
    }
}
