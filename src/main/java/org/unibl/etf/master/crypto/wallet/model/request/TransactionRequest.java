package org.unibl.etf.master.crypto.wallet.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record TransactionRequest(@NotNull @Min(0) double amount, @NotNull @NotEmpty String hash) { }