package org.unibl.etf.master.crypto.wallet.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public record WinRequest(@NotEmpty String walletHash, @Min(0) double amount) { }