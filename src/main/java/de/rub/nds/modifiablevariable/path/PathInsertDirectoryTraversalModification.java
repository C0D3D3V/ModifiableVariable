/*
 * ModifiableVariable - A Variable Concept for Runtime Modifications
 *
 * Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0 http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.modifiablevariable.path;

import de.rub.nds.modifiablevariable.VariableModification;
import de.rub.nds.modifiablevariable.util.IllegalStringAdapter;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;
import java.util.Random;

/** Modification that directory traversal path parts the original value. */
@XmlRootElement
@XmlType(propOrder = {"insertValue", "count", "startPosition", "modificationFilter"})
public class PathInsertDirectoryTraversalModification extends VariableModification<String> {

    private static final int MAX_INSERT_MODIFIER = 32;

    @XmlJavaTypeAdapter(IllegalStringAdapter.class)
    private String insertValue;

    private int count;
    private int startPosition;

    public PathInsertDirectoryTraversalModification() {
        super();
    }

    public PathInsertDirectoryTraversalModification(int count, int startPosition) {
        super();
        this.count = count;
        this.startPosition = startPosition;
        updateInsertValue();
    }

    public PathInsertDirectoryTraversalModification(
            PathInsertDirectoryTraversalModification other) {
        super(other);
        insertValue = other.insertValue;
        count = other.count;
        startPosition = other.startPosition;
    }

    @Override
    public PathInsertDirectoryTraversalModification createCopy() {
        return new PathInsertDirectoryTraversalModification(this);
    }

    private void updateInsertValue() {
        StringBuilder builder = new StringBuilder(count * 3 - 1);
        for (int i = 0; i < count; i++) {
            builder.append("..");
            if (i < count - 1) {
                builder.append("/");
            }
        }
        insertValue = builder.toString();
    }

    @Override
    protected String modifyImplementationHook(String input) {
        return PathUtil.insertValueAsPathPart(input, insertValue, startPosition);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        updateInsertValue();
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    @Override
    public VariableModification<String> getModifiedCopy() {
        Random r = new Random();

        if (r.nextBoolean()) {
            int modifier = r.nextInt(MAX_INSERT_MODIFIER);
            if (r.nextBoolean()) {
                modifier *= -1;
            }
            modifier = count + modifier;
            if (modifier <= 0) {
                modifier = 1;
            }
            return new PathInsertDirectoryTraversalModification(modifier, startPosition);
        } else {
            int modifier = r.nextInt(MAX_INSERT_MODIFIER);
            if (r.nextBoolean()) {
                modifier *= -1;
            }
            modifier = startPosition + modifier;
            if (modifier <= 0) {
                modifier = 1;
            }
            return new PathInsertDirectoryTraversalModification(count, modifier);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + count;
        hash = 31 * hash + startPosition;
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
        PathInsertDirectoryTraversalModification other =
                (PathInsertDirectoryTraversalModification) obj;
        if (startPosition != other.startPosition) {
            return false;
        }
        return Objects.equals(count, other.count);
    }
}
