package org.example;

import edu.illinois.library.cantaloupe.delegate.AbstractJavaDelegate;
import edu.illinois.library.cantaloupe.delegate.JavaDelegate;
import edu.illinois.library.cantaloupe.delegate.Logger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MyJavaDelegate extends AbstractJavaDelegate implements JavaDelegate {

    @Override
    public String serializeMetaIdentifier(Map<String,Object> metaIdentifier) {
        return null;
    }

    @Override
    public Map<String,Object> deserializeMetaIdentifier(String metaIdentifier) {
        return null;
    }

    @Override
    public Object preAuthorize() {
        Logger.info("Hello world! The identifier is: " + getContext().getIdentifier());

        // 50-50 chance of redirecting to a 1:2 scale resource
        if (!Arrays.equals(getContext().getScaleConstraint(), new int[] {1, 2}) && Math.round(Math.random()) == 1) {
            if (getContext().getIdentifier().contains("/")) {
                Logger.info("🔮🧙 Ah, slashes... I see a 404 in your future...");
            }
            return Map.of("status_code", 302L, "scale_numerator", 1L, "scale_denominator", 2L);
        } else {
            return true;
        }
    }

    @Override
    public Object authorize() {
        return true;
    }

    @Override
    public Map<String,Object> getExtraIIIF2InformationResponseKeys() {
        return Collections.emptyMap();
    }

    @Override
    public Map<String,Object> getExtraIIIF3InformationResponseKeys() {
        return Collections.emptyMap();
    }

    @Override
    public String getSource() {
        return null;
    }

    @Override
    public String getAzureStorageSourceBlobKey() {
        return null;
    }

    @Override
    public String getFilesystemSourcePathname() {
        return null;
    }

    @Override
    public Map<String,Object> getHTTPSourceResourceInfo() {
        return null;
    }

    @Override
    public String getJDBCSourceDatabaseIdentifier() {
        return null;
    }

    @Override
    public String getJDBCSourceMediaType() {
        return null;
    }

    @Override
    public String getJDBCSourceLookupSQL() {
        return null;
    }

    @Override
    public String getJDBCSourceLastModified() {
        return null;
    }

    @Override
    public Map<String,String> getS3SourceObjectInfo() {
        return null;
    }

    @Override
    public Map<String,Object> getOverlay() {
        return null;
    }

    @Override
    public List<Map<String,Long>> getRedactions() {
        return Collections.emptyList();
    }

    @Override
    public String getMetadata() {
        return null;
    }

}

