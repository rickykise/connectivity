package ai.fassto.connectivity.domain.vendor.application.port.output.external.api.wms;

import ai.fassto.connectivity.domain.vendor.core.entity.Vendor;

public interface VendorWms {
    Vendor create(Vendor vendor);

    Vendor update(Vendor vendor);
}
