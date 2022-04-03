package org.unibl.etf.master.crypto.wallet.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record TransactionRequest(@NotNull @Min(1) Long amount) { }