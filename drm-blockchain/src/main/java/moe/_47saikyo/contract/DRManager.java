package moe._47saikyo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.2.
 */
@SuppressWarnings("rawtypes")
public class DRManager extends Contract {
    public static final String BINARY = "6080604052348015600f57600080fd5b50610a918061001f6000396000f3fe608060405234801561001057600080fd5b50600436106100b45760003560e01c8063a5b06b3b11610071578063a5b06b3b14610150578063b93a9a4614610158578063ccaf08041461016b578063d6a7b9761461017e578063e79f0e9614610191578063ee82ff8f146101a457600080fd5b806314773b4f146100b957806321875110146100e257806326eb44f7146100f7578063490c8ce2146101225780636c775a8f1461012a5780638a28fc531461013d575b600080fd5b6100cc6100c736600461064e565b6101b7565b6040516100d9919061066b565b60405180910390f35b6100f56100f036600461064e565b610282565b005b61010a61010536600461077d565b61028f565b6040516001600160a01b0390911681526020016100d9565b6100cc6102b8565b61010a61013836600461077d565b6102c8565b6100f561014b3660046107cd565b6102ea565b6100cc610382565b6100f56101663660046107cd565b61038d565b6100f561017936600461064e565b6103f2565b61010a61018c366004610806565b610446565b6100cc61019f36600461064e565b610458565b61010a6101b2366004610806565b61051b565b6001600160a01b0381166000908152600160208190526040822001546060918167ffffffffffffffff8111156101ef576101ef6106b8565b604051908082528060200260200182016040528015610218578160200160208202803683370190505b50905060005b8281101561027a576001600160a01b03851660009081526001602052604090206102489082610527565b82828151811061025a5761025a610843565b6001600160a01b039092166020928302919091019091015260010161021e565b509392505050565b61028c338261038d565b50565b6001600160a01b03821660009081526001602052604081206102b19083610562565b9392505050565b60606102c333610458565b905090565b6001600160a01b03821660009081526020819052604081206102b19083610562565b61037e60016000846001600160a01b03166001600160a01b03168152602001908152602001600020826001600160a01b03166382678dd66040518163ffffffff1660e01b8152600401600060405180830381865afa158015610350573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f19168201604052610378919081019061087d565b83610599565b5050565b60606102c3336101b7565b61037e600080846001600160a01b03166001600160a01b03168152602001908152602001600020826001600160a01b03166382678dd66040518163ffffffff1660e01b8152600401600060405180830381865afa158015610350573d6000803e3d6000fd5b336000908152600160205260408082208151634133c6eb60e11b8152915161028c9391926001600160a01b038616926382678dd6926004808401938290030181865afa158015610350573d6000803e3d6000fd5b6000610452338361028f565b92915050565b6001600160a01b0381166000908152602081905260408120600101546060918167ffffffffffffffff811115610490576104906106b8565b6040519080825280602002602001820160405280156104b9578160200160208202803683370190505b50905060005b8281101561027a576001600160a01b03851660009081526020819052604090206104e99082610527565b8282815181106104fb576104fb610843565b6001600160a01b03909216602092830291909101909101526001016104bf565b600061045233836102c8565b600082600101828154811061053e5761053e610843565b60009182526020909120600290910201600101546001600160a01b03169392505050565b600082600101836000018360405161057a91906108f4565b9081526020016040518091039020548154811061053e5761053e610843565b60018084018054604080518082019091528581526001600160a01b03851660208083019190915293820183556000928352929091208251919291600284029091019081906105e7908261099b565b5060209190910151600190910180546001600160a01b0319166001600160a01b03909216919091179055604051819085906106239086906108f4565b9081526040519081900360200190205550505050565b6001600160a01b038116811461028c57600080fd5b60006020828403121561066057600080fd5b81356102b181610639565b6020808252825182820181905260009190848201906040850190845b818110156106ac5783516001600160a01b031683529284019291840191600101610687565b50909695505050505050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff811182821017156106f7576106f76106b8565b604052919050565b600067ffffffffffffffff821115610719576107196106b8565b50601f01601f191660200190565b600082601f83011261073857600080fd5b813561074b610746826106ff565b6106ce565b81815284602083860101111561076057600080fd5b816020850160208301376000918101602001919091529392505050565b6000806040838503121561079057600080fd5b823561079b81610639565b9150602083013567ffffffffffffffff8111156107b757600080fd5b6107c385828601610727565b9150509250929050565b600080604083850312156107e057600080fd5b82356107eb81610639565b915060208301356107fb81610639565b809150509250929050565b60006020828403121561081857600080fd5b813567ffffffffffffffff81111561082f57600080fd5b61083b84828501610727565b949350505050565b634e487b7160e01b600052603260045260246000fd5b60005b8381101561087457818101518382015260200161085c565b50506000910152565b60006020828403121561088f57600080fd5b815167ffffffffffffffff8111156108a657600080fd5b8201601f810184136108b757600080fd5b80516108c5610746826106ff565b8181528560208385010111156108da57600080fd5b6108eb826020830160208601610859565b95945050505050565b60008251610906818460208701610859565b9190910192915050565b600181811c9082168061092457607f821691505b60208210810361094457634e487b7160e01b600052602260045260246000fd5b50919050565b601f821115610996576000816000526020600020601f850160051c810160208610156109735750805b601f850160051c820191505b818110156109925782815560010161097f565b5050505b505050565b815167ffffffffffffffff8111156109b5576109b56106b8565b6109c9816109c38454610910565b8461094a565b602080601f8311600181146109fe57600084156109e65750858301515b600019600386901b1c1916600185901b178555610992565b600085815260208120601f198616915b82811015610a2d57888601518255948401946001909101908401610a0e565b5085821015610a4b5787850151600019600388901b60f8161c191681555b5050505050600190811b0190555056fea264697066735822122035e0f942b3c01954472fa9d2d343ac2a1b39a7406d767ccf64f1babe23d2ebfe64736f6c63430008190033";

    public static final String FUNC_addLicense = "addLicense";

    public static final String FUNC_addRight = "addRight";

    public static final String FUNC_getLicense = "getLicense";

    public static final String FUNC_getLicenses = "getLicenses";

    public static final String FUNC_getRight = "getRight";

    public static final String FUNC_getRights = "getRights";

    @Deprecated
    protected DRManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DRManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DRManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DRManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addLicense(String owner, String license) {
        final Function function = new Function(
                FUNC_addLicense, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, license)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addLicense(String license) {
        final Function function = new Function(
                FUNC_addLicense, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, license)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addRight(String right) {
        final Function function = new Function(
                FUNC_addRight, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, right)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addRight(String owner, String right) {
        final Function function = new Function(
                FUNC_addRight, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, right)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getLicense(String owner, String key) {
        final Function function = new Function(FUNC_getLicense, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Utf8String(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getLicense(String key) {
        final Function function = new Function(FUNC_getLicense, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getLicenses(String owner) {
        final Function function = new Function(FUNC_getLicenses, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<List> getLicenses() {
        final Function function = new Function(FUNC_getLicenses, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> getRight(String owner, String key) {
        final Function function = new Function(FUNC_getRight, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Utf8String(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getRight(String key) {
        final Function function = new Function(FUNC_getRight, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getRights() {
        final Function function = new Function(FUNC_getRights, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<List> getRights(String owner) {
        final Function function = new Function(FUNC_getRights, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    @Deprecated
    public static DRManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DRManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DRManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DRManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DRManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DRManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DRManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DRManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DRManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DRManager.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DRManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DRManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<DRManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DRManager.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DRManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DRManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
