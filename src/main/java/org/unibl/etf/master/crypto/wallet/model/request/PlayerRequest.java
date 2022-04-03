package org.unibl.etf.master.crypto.wallet.model.request;

import javax.validation.constraints.Size;

public record PlayerRequest(@Size(min = 2, max = 3) String currency, long credit, String externalId) { }