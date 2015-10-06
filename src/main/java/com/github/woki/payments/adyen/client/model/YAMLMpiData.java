package com.github.woki.payments.adyen.client.model;

import com.github.woki.payments.adyen.model.ThreeDSecureData;
import com.github.woki.payments.adyen.model.ThreeDSecureDataBuilder;

/**
 * Created by Willian Oki on 10/6/15.
 */
@SuppressWarnings("unused")
public class YAMLMpiData {
    public enum AuthenticationResponse {
        Y, N, U, A
    }
    public enum DirectoryResponse {
        Y, N, U, E
    }
    private AuthenticationResponse authenticationResponse;
    private String cavv;
    private String cavvAlgorithm;
    private DirectoryResponse directoryResponse;
    private String eci;
    private String xid;

    public ThreeDSecureData toThreeDSecureData() {
        return ThreeDSecureDataBuilder
                .authenticationResponse(authenticationResponse.name())
                .cavv(cavv, cavvAlgorithm)
                .directoryResponse(directoryResponse.name())
                .eci(eci)
                .xid(xid)
                .build();
    }

    public AuthenticationResponse getAuthenticationResponse() {
        return authenticationResponse;
    }

    public void setAuthenticationResponse(AuthenticationResponse authenticationResponse) {
        this.authenticationResponse = authenticationResponse;
    }

    public String getCavv() {
        return cavv;
    }

    public void setCavv(String cavv) {
        this.cavv = cavv;
    }

    public String getCavvAlgorithm() {
        return cavvAlgorithm;
    }

    public void setCavvAlgorithm(String cavvAlgorithm) {
        this.cavvAlgorithm = cavvAlgorithm;
    }

    public DirectoryResponse getDirectoryResponse() {
        return directoryResponse;
    }

    public void setDirectoryResponse(DirectoryResponse directoryResponse) {
        this.directoryResponse = directoryResponse;
    }

    public String getEci() {
        return eci;
    }

    public void setEci(String eci) {
        this.eci = eci;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }
}
