package org.example;

import edu.illinois.library.cantaloupe.delegate.AbstractJavaDelegate;
import edu.illinois.library.cantaloupe.delegate.JavaDelegate;
import edu.illinois.library.cantaloupe.delegate.Logger;

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

        switch (getRequestType()) {
        case INFORMATION:
            if (hasValidToken()) {
                return true;
            } else {
                return Map.of("status_code", Long.valueOf(401), "challenge", "Bearer charset=\"UTF-8\"");
            }
        case IMAGE:
        default:
            if (hasValidCookie()) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public Object authorize() {
        return true;
    }

    @Override
    public Map<String,Object> getExtraIIIF2InformationResponseKeys() {
        return getExtraIIIF3InformationResponseKeys();

    }

    @Override
    public Map<String,Object> getExtraIIIF3InformationResponseKeys() {
        return Collections.singletonMap(
            "service", Map.of(
                "@context", "http://iiif.io/api/auth/1/context.json",
                "@id", "https://authentication.example.org/login",
                "profile", "http://iiif.io/api/auth/1/login",
                "label", "Login to Example Institution",
                "service", Map.of(
                    "@id", "https://authentication.example.org/token",
                    "profile", "http://iiif.io/api/auth/1/token"
                )
            )
        );
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

    private enum RequestType {
        INFORMATION, IMAGE;
    }

    private RequestType getRequestType() {
        String requestURI = getContext().getRequestURI();

        if (requestURI.endsWith("info.json")) {
            return RequestType.INFORMATION;
        } else {
            return RequestType.IMAGE;
        }
    }

    private boolean hasValidToken() {
        // In reality, we'd check the Authorization header
        boolean isValid = Math.round(Math.random()) == 1;

        Logger.debug("Valid token? (random): " + isValid);

        return isValid;
    }

    private boolean hasValidCookie() {
        Logger.debug("Checking for a \"iiif-access=authorized\" cookie...");

        if (getContext().getCookies().get("iiif-access").equals("authorized")) {
            Logger.debug("Cookie found.");

            return true;
        } else {
            Logger.debug("Cookie not found.");

            return false;
        }
    }

}

