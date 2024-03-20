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
    public static final String BINARY = "6080604052348015600f57600080fd5b506106cc8061001f6000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c8063218751101461004657806348e02aff1461005b578063ee82ff8f14610079575b600080fd5b610059610054366004610312565b6100a4565b005b610063610129565b6040516100709190610342565b60405180910390f35b61008c6100873660046103fe565b6101e1565b6040516001600160a01b039091168152602001610070565b336000908152602081905260408082208151631e3b582d60e01b815291516101269391926001600160a01b03861692631e3b582d926004808401938290030181865afa1580156100f8573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261012091908101906104a2565b83610200565b50565b336000908152602081905260408120600101546060918167ffffffffffffffff8111156101585761015861038f565b604051908082528060200260200182016040528015610181578160200160208202803683370190505b50905060005b828110156101da573360009081526020819052604090206101a890826102a0565b8282815181106101ba576101ba610519565b6001600160a01b0390921660209283029190910190910152600101610187565b5092915050565b3360009081526020819052604081206101fa90836102db565b92915050565b60018084018054604080518082019091528581526001600160a01b038516602080830191909152938201835560009283529290912082519192916002840290910190819061024e90826105ba565b5060209190910151600190910180546001600160a01b0319166001600160a01b039092169190911790556040518190859061028a90869061067a565b9081526040519081900360200190205550505050565b60008260010182815481106102b7576102b7610519565b60009182526020909120600290910201600101546001600160a01b03169392505050565b60008260010183600001836040516102f3919061067a565b908152602001604051809103902054815481106102b7576102b7610519565b60006020828403121561032457600080fd5b81356001600160a01b038116811461033b57600080fd5b9392505050565b6020808252825182820181905260009190848201906040850190845b818110156103835783516001600160a01b03168352928401929184019160010161035e565b50909695505050505050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff811182821017156103ce576103ce61038f565b604052919050565b600067ffffffffffffffff8211156103f0576103f061038f565b50601f01601f191660200190565b60006020828403121561041057600080fd5b813567ffffffffffffffff81111561042757600080fd5b8201601f8101841361043857600080fd5b803561044b610446826103d6565b6103a5565b81815285602083850101111561046057600080fd5b81602084016020830137600091810160200191909152949350505050565b60005b83811015610499578181015183820152602001610481565b50506000910152565b6000602082840312156104b457600080fd5b815167ffffffffffffffff8111156104cb57600080fd5b8201601f810184136104dc57600080fd5b80516104ea610446826103d6565b8181528560208385010111156104ff57600080fd5b61051082602083016020860161047e565b95945050505050565b634e487b7160e01b600052603260045260246000fd5b600181811c9082168061054357607f821691505b60208210810361056357634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156105b5576000816000526020600020601f850160051c810160208610156105925750805b601f850160051c820191505b818110156105b15782815560010161059e565b5050505b505050565b815167ffffffffffffffff8111156105d4576105d461038f565b6105e8816105e2845461052f565b84610569565b602080601f83116001811461061d57600084156106055750858301515b600019600386901b1c1916600185901b1785556105b1565b600085815260208120601f198616915b8281101561064c5788860151825594840194600190910190840161062d565b508582101561066a5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b6000825161068c81846020870161047e565b919091019291505056fea2646970667358221220385a239a96ef16ae2b575a9454f63cecb7adc3734876579d5e66b4ba841458ea64736f6c63430008190033";

    public static final String FUNC_ADDRIGHT = "addRight";

    public static final String FUNC_GETALLRIGHTS = "getAllRights";

    public static final String FUNC_GETRIGHT = "getRight";

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

    public RemoteFunctionCall<TransactionReceipt> addRight(String right) {
        final Function function = new Function(
                FUNC_ADDRIGHT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, right)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getAllRights() {
        final Function function = new Function(FUNC_GETALLRIGHTS, 
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

    public RemoteFunctionCall<String> getRight(String rightName) {
        final Function function = new Function(FUNC_GETRIGHT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(rightName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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
